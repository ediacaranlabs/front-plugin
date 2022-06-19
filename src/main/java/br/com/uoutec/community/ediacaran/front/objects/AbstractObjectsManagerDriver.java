package br.com.uoutec.community.ediacaran.front.objects;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.Filter;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectValue;

public abstract class AbstractObjectsManagerDriver implements ObjectsManagerDriver {

	public static final String basePermission = "app.objs.driver.";
	
	private String name;
	
	private List<ObjectHandler> handlers;
	
	private ObjectHandler defaultObjectHandler;
	
	private ReadWriteLock handlersLock;
	
	private ObjectsManagerDriverListenerWrapper listeners;
	
	public AbstractObjectsManagerDriver(String name) {
		this.name = name;
		this.handlersLock = new ReentrantReadWriteLock();
		this.handlers = new LinkedList<ObjectHandler>();
		this.listeners = new ObjectsManagerDriverListenerWrapper();
	}
	
	public String getName() {
		return name;
	}
	
	public void addListener(ObjectsManagerDriverListener listener) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + name + ".listener.register"));
		}
		
		listeners.registerListener(listener);
	}

	public void removeListener(ObjectsManagerDriverListener listener) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + name + ".listener.unregister"));
		}
		
		listeners.unregisterListener(listener);
	}
	
	@Override
	public void registerObjectHandler(ObjectHandler handler) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + name + ".handler.register"));
		}
		
		if(handlers.indexOf(handler) != -1) {
			throw new IllegalStateException("handler");
		}
		
		Lock lock = handlersLock.writeLock();
		lock.lock();
		try {
			handlers.add(handler);
		}
		finally {
			lock.unlock();
		}

	}

	@Override
	public void unregisterObjectHandler(ObjectHandler handler) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + name + ".handler.unregister"));
		}

		Lock lock = handlersLock.writeLock();
		lock.lock();
		try {
			handlers.remove(handler);
		}
		finally {
			lock.unlock();
		}
		
	}

	public ObjectHandler getDefaultObjectHandler() {
		return defaultObjectHandler;
	}

	public void setDefaultObjectHandler(ObjectHandler defaultObjectHandler) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + name + ".default_handler.register"));
		}
		
		
		this.defaultObjectHandler = defaultObjectHandler;
	}

	@Override
	public ObjectMetadata unique(){
		return unique(null, null);
	}
	
	@Override
	public ObjectMetadata unique(Filter filter){
		return unique(null, true, filter);
	}

	@Override
	public ObjectMetadata unique(String path, Filter filter){
		return unique(path, true, filter);
	}

	@Override
	public ObjectMetadata unique(String path, boolean recursive, Filter filter) {
		
		List<ObjectMetadata> list = list(path, recursive, filter);
		
		if(list.size() > 1) {
			throw new IllegalStateException("found: " + list.size());
		}
		
		return list.isEmpty()? null : list.get(0);
	}

	@Override
	public List<ObjectMetadata> list(){
		return list(null);
	}
	
	@Override
	public List<ObjectMetadata> list(Filter filter){
		return list(null, true, filter);
	}

	@Override
	public List<ObjectMetadata> list(String path, Filter filter){
		return list(path, true, filter);
	}

	public ObjectValue get(ObjectMetadata omd) {
		listeners.beforeLoad(omd);
		ObjectValue o = getAction(omd);
		listeners.afterLoad(omd, o);
		return o;
	}

	protected abstract ObjectValue getAction(ObjectMetadata omd);
	
	public ObjectValue persist(String path, String name, Locale locale, Object obj) throws ObjectsManagerDriverException  {
		listeners.beforePersist(path, name, locale, obj);
		ObjectValue o = persistAction(path, name, locale, obj);
		listeners.afterPersist(path, name, locale, o);
		return o;
	}
	
	protected abstract ObjectValue persistAction(String path, String name, Locale locale, Object obj) throws ObjectsManagerDriverException;
	
	/* delete */
	
	public void delete(ObjectMetadata omd) throws ObjectsManagerDriverException {
		listeners.beforeDelete(omd);
		deleteAction(omd);
		listeners.afterDelete(omd);
	}
	
	protected abstract void deleteAction(ObjectMetadata omd) throws ObjectsManagerDriverException;
	
	protected ObjectHandler getObjectHandler(Object obj) throws ObjectsManagerDriverException {
		
		Lock lock = handlersLock.readLock();
		lock.lock();
		try {
			for(ObjectHandler handler: handlers) {
				if(handler.accept(obj)) {
					return handler;
				}
			}
		}
		finally {
			lock.unlock();
		}
		
		return defaultObjectHandler;
	}

	protected ObjectHandler getObjectHandler(String type) throws ObjectsManagerDriverException {
		
		Lock lock = handlersLock.readLock();
		lock.lock();
		try {
			for(ObjectHandler handler: handlers) {
				if(handler.accept(type)) {
					return handler;
				}
			}
		}
		finally {
			lock.unlock();
		}
		
		return defaultObjectHandler;
	}
	
	private static class ObjectsManagerDriverListenerWrapper implements ObjectsManagerDriverListener{

		private List<ObjectsManagerDriverListener> listeners;
		
		private ReadWriteLock readWriteLock;
		
		public ObjectsManagerDriverListenerWrapper() {
			this.listeners = new LinkedList<ObjectsManagerDriverListener>();
			this.readWriteLock = new ReentrantReadWriteLock();
		}
		
		public void registerListener(ObjectsManagerDriverListener listener) {
			Lock lock = readWriteLock.writeLock();
			lock.lock();
			try {
				listeners.add(listener);
			}
			finally {
				lock.unlock();
			}
		}

		public void unregisterListener(ObjectsManagerDriverListener listener) {
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
		public void beforeLoad(ObjectMetadata omd) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectsManagerDriverListener l: listeners) {
					l.beforeLoad(omd);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void afterLoad(ObjectMetadata omd, ObjectValue obj) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectsManagerDriverListener l: listeners) {
					l.afterLoad(omd, obj);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void beforePersist(String path, String name, Locale locale, Object obj) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectsManagerDriverListener l: listeners) {
					l.beforePersist(path, name, locale, obj);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void afterPersist(String path, String name, Locale locale, ObjectValue objValue) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectsManagerDriverListener l: listeners) {
					l.afterPersist(path, name, locale, objValue);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void beforeDelete(ObjectMetadata omd) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectsManagerDriverListener l: listeners) {
					l.beforeDelete(omd);
				}
			}
			finally {
				lock.unlock();
			}
		}

		@Override
		public void afterDelete(ObjectMetadata omd) {
			Lock lock = readWriteLock.readLock();
			lock.lock();
			try {
				for(ObjectsManagerDriverListener l: listeners) {
					l.afterDelete(omd);
				}
			}
			finally {
				lock.unlock();
			}
		}
		
	}
	
}
