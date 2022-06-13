package br.com.uoutec.community.ediacaran.front.pub;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.pub.ObjectFileManager.ObjectValue;
import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@Singleton
public class ObjectsManagerImp 
	implements ObjectsManager, PublicBean {

	public static final String basePermission = "app.objs";
	
	private String OBJECTS_REPOSITORY = "/objects";
	
	private ConcurrentMap<String, ConcurrentMap<Locale,ObjectValue>> objects;
	
	private ReadWriteLock readWriteLock;
	
	private Lock writeLock;
	
	private Lock readLock;
	
	private List<ObjectHandler> handlers;
	
	private ObjectListenerManager objectListenerManager;
	
	private ObjectFileManager objectFileManager;
	
	public ObjectsManagerImp() {
		handlers = new LinkedList<ObjectHandler>();
		objects = new ConcurrentHashMap<String, ConcurrentMap<Locale,ObjectValue>>();
		this.readWriteLock = new ReentrantReadWriteLock();
		this.writeLock = readWriteLock.writeLock();
		this.readLock = readWriteLock.readLock();
		this.objectListenerManager = new ObjectListenerManager();
		this.objectFileManager = new ObjectFileManager(new File(System.getProperty("app.base"), OBJECTS_REPOSITORY));
	}
	
	@Override
	public void registerObject(String id, Locale locale, Object object) {
		
		if(object == null) {
			throw new NullPointerException("object");
		}
		
		if(!objectFileManager.isValidFullId(id)) {
			throw new IllegalStateException("invalid id: " + id);
		}

		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + id.replace("/", ".") + ".register"));
		}

		writeLock.lock();
		try {
			
			objectListenerManager.beforeRegister(id, locale, object);
			
			ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
			
			if(map == null) {
				map = new ConcurrentHashMap<Locale,ObjectValue>();
				ConcurrentMap<Locale,ObjectValue> current = objects.putIfAbsent(id, map);
				if(current != null) {
					map = current;
				}
			}
	
			ObjectHandler handler = getObjectHandler(object);
			ObjectValue obj = persistObject(id, locale, object, handler);
			map.put(locale, obj);
			
			objectListenerManager.afterRegister(id, locale, object);
			
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
		finally {
			writeLock.unlock();
		}
		
		
	}

	@Override
	public void unregisterObject(String id, Locale locale) {

		if(!objectFileManager.isValidFullId(id)) {
			throw new IllegalStateException("invalid format: " + id);
		}
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + id.replace("/", ".") + ".unregister"));
		}
		
		writeLock.lock();
		try {
			
			int lastIndex = id.lastIndexOf("/");
			String path = id.substring(0, lastIndex - 1);
			String name = id.substring(lastIndex);
			
			ObjectMetadata omd = objectFileManager.unique(path, false, e->{
				return name.equals(e.getId()) && locale == e.getLocale();
			});
			
			if(omd == null) {
				return;
			}

			objectListenerManager.beforeUnregister(id, locale);

			ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
			
			if(map != null) {
				map.remove(locale);
				if(map.isEmpty()) {
					objects.remove(id, map);
				}
			}
			
			deleteObject(omd);
			
			objectListenerManager.afterUnregister(id, locale);
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
		finally {
			writeLock.unlock();
		}
		
	}

	@Override
	public synchronized void registerObjectHandler(ObjectHandler handler) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".handler.register"));
		}
		
		if(handlers.indexOf(handler) != -1) {
			throw new IllegalStateException("handler");
		}
		
		handlers.add(handler);
	}

	@Override
	public synchronized void unregisterObjectHandler(ObjectHandler handler) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".handler.unregister"));
		}
		
		handlers.remove(handler);
	}

	@Override
	public Object getObject(String id) {
		return getObject(id, null);
	}

	@Override
	public Object getObject(String id, Locale locale) {
		
		readLock.lock();
		try {
			Object object = get(id, locale);
			
			if(object == null && locale != null) {
				object = get(id, null);
			}
			
			return object;
		}
		finally {
			readLock.unlock();
		}
		
	}

	@Override
	public ObjectEntry getObjects(String id) {
		
		readLock.lock();
		try {
			int lastIndex = id.lastIndexOf("/");
			String path = id.substring(0, lastIndex - 1);
			String name = id.substring(lastIndex);
			
			List<ObjectMetadata> list = objectFileManager.list(path, false, e->{
				return name.equals(e.getId());
			});
			
			Map<Locale,Object> map = new HashMap<Locale, Object>();
			for(ObjectMetadata omd: list) {
				Object o = get(id, omd.getLocale());
				if(o != null) {
					map.put(omd.getLocale(), o);
				}
			}
			return map.isEmpty()? null : new ObjectEntry(path, name, map);
		}
		finally {
			readLock.unlock();
		}
			
	}
	
	public void addListener(ObjectListener listener) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".listener.register"));
		}
		
		objectListenerManager.registerListener(listener);
	}

	public void removeListener(ObjectListener listener) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".listener.unregister"));
		}
		
		objectListenerManager.unregisterListener(listener);
	}
	
	/* --private-- */
	
	/* get */
	
	private Object get(String id, Locale locale) {
		
		ObjectValue obj;
		ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
		
		if(map != null) {
			obj = map.get(locale);
			
			if(obj != null && obj.isValid()) {
				return obj;
			}
		}
		
		try {
			obj = loadObject(id, locale);
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
		
		return obj == null? null : obj.object; 
	}
	
	private synchronized ObjectValue loadObject(String id, Locale locale) throws IOException {
		
		ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
		
		if(map != null) {
			ObjectValue obj = map.get(locale);
			if(obj != null && obj.isValid()) {
				return obj;
			}
		}
		
		int lastIndex = id.lastIndexOf("/");
		String path = id.substring(0, lastIndex - 1);
		String name = id.substring(lastIndex);
		
		ObjectMetadata omd = objectFileManager.unique(path, false, e->{
			return name.equals(e.getId()) && locale == e.getLocale();
		});
		
		if(omd != null) {
			
			ObjectHandler handler = getObjectHandler(omd.getType());
			
			objectListenerManager.beforeLoad(id, locale);
			
			ObjectValue object = objectFileManager.get(omd, handler);
			
			objectListenerManager.afterLoad(id, locale, object);
			
			map = new ConcurrentHashMap<Locale,ObjectValue>();
			ConcurrentMap<Locale,ObjectValue> current = objects.putIfAbsent(id, map);
			if(current != null) {
				map = current;
			}
			map.put(locale, object);
			
			return object;
		}
		
		map = objects.get(id);
		
		if(map != null ) {
			map.remove(locale);
			if(map.isEmpty()) {
				objects.remove(id);
			}
		}
		
		return null;
	}
	
	/* persist */
	
	private synchronized ObjectValue persistObject(String id, Locale locale, Object obj, ObjectHandler handler) throws IOException {
		File file = objectFileManager.persist(id, locale, obj, handler);
		return new ObjectValue(file, obj);
	}

	/* delete */
	
	private synchronized void deleteObject(ObjectMetadata omd) throws IOException {
		objectFileManager.delete(omd);
	}
	
	/* base */
	
	private ObjectHandler getObjectHandler(Object obj) {
		
		for(ObjectHandler handler: handlers) {
			if(handler.accept(obj)) {
				return handler;
			}
		}
		
		throw new NullPointerException("handler not found: " + obj);
		
	}

	private ObjectHandler getObjectHandler(String type) {
		
		for(ObjectHandler handler: handlers) {
			if(handler.accept(type)) {
				return handler;
			}
		}
		
		throw new NullPointerException("handler not found: " + type);
		
	}
	
	private static class ObjectListenerManager implements ObjectListener{

		private Set<ObjectListener> listeners;
		
		private ReadWriteLock readWriteLock;
		
		public void registerListener(ObjectListener listener) {
			Lock lock = readWriteLock.writeLock();
			lock.lock();
			try {
				listeners.add(listener);
			}
			finally {
				lock.unlock();
			}
		}

		public void unregisterListener(ObjectListener listener) {
			Lock lock = readWriteLock.writeLock();
			lock.lock();
			try {
				listeners.remove(listener);
			}
			finally {
				lock.unlock();
			}
		}
		
		@Override
		public void beforeLoad(String id, Locale locale) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectListener l: listeners) {
					l.beforeLoad(id, locale);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void afterLoad(String id, Locale locale, Object obj) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectListener l: listeners) {
					l.afterLoad(id, locale, obj);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void beforeRegister(String id, Locale locale, Object object) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectListener l: listeners) {
					l.beforeRegister(id, locale, object);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void afterRegister(String id, Locale locale, Object object) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectListener l: listeners) {
					l.afterRegister(id, locale, object);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void beforeUnregister(String id, Locale locale) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectListener l: listeners) {
					l.beforeUnregister(id, locale);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void afterUnregister(String id, Locale locale) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectListener l: listeners) {
					l.afterUnregister(id, locale);
				}
			}
			finally {
				lock.unlock();
			}
		}
		
	}
	
}
