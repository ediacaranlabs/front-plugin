package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;
import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@Singleton
public class ObjectsManagerImp 
	implements ObjectsManager, PublicBean {

	private static final String PATH_FORMAT = "(\\/[a-z][a-z0-9]+(_[a-z0-9]+)*)+";
	
	private static final String ID_FORMAT = "[a-z][a-z0-9]+(_[a-z0-9]+)*";

	private static final String FULLID_FORMAT = "(" + PATH_FORMAT + ")\\/(" + ID_FORMAT + ")";
	
	private Pattern fullIdPattern = Pattern.compile(FULLID_FORMAT);
	
	public static final String basePermission = "app.objs";
	
	public static final String OBJECTS_REPOSITORY = "objects/";
	
	private static final Locale NULL_LOCALE = new Locale("xx", "XX");
	
	private ConcurrentMap<String, ConcurrentMap<Locale,ObjectValue>> objects;
	
	private ReadWriteLock readWriteLock;
	
	private Lock writeLock;
	
	private Lock readLock;
	
	private ConcurrentMap<String,ObjectsManagerDriver> drivers;
	
	private ObjectListenerManager objectListenerManager;
	
	private ObjectFileManager objectFileManager;
	
	public ObjectsManagerImp() {
		drivers = new ConcurrentHashMap<String,ObjectsManagerDriver>();
		objects = new ConcurrentHashMap<String, ConcurrentMap<Locale,ObjectValue>>();
		this.readWriteLock = new ReentrantReadWriteLock();
		this.writeLock = readWriteLock.writeLock();
		this.readLock = readWriteLock.readLock();
		this.objectListenerManager = new ObjectListenerManager();
		this.objectFileManager = new ObjectFileManager(new File(System.getProperty("app.base"), OBJECTS_REPOSITORY));
	}
	
	private boolean isVaidID(String value) {
		return fullIdPattern.matcher(value).matches();
	}
	
	private String[] toPathAndName(String fullId) {
		
		if(!isVaidID(fullId)) {
			return null;
		}
		
		fullId = fullId.trim();
		
		int lastIndex = fullId.lastIndexOf("/");
		
		if(lastIndex == 0) {
			return new String[] {fullId,null};
		}
		
		return new String[] {fullId.substring(0, lastIndex), fullId.substring(lastIndex + 1)};
		
	}
	
	/* /global/admin/menus/adminmenubar */
	/* /local/index */
	/* /mysql/admin/menus/adminmenubar */
	/* /mysql/users/name/afonso%brandao */
	/* /mysql/users/id/afonso%brandao */
	
	@Override
	public void registerObject(String id, Locale locale, Object object) {
		
		if(object == null) {
			throw new NullPointerException("object");
		}
		
		if(!isVaidID(id)) {
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
	
			String[] pathAndName = toPathAndName(id);
			String path = pathAndName[0];
			String name = pathAndName[1];
			
			ObjectValue obj = persistObject(path, name, locale, object);
			
			if(obj.isValid()) {
				map.put(getSafeLocale(locale), obj);
			}
			
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

		if(!isVaidID(id)) {
			throw new IllegalStateException("invalid format: " + id);
		}
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + id.replace("/", ".") + ".unregister"));
		}
		
		writeLock.lock();
		try {
			
			String[] pathAndName = toPathAndName(id);
			String path = pathAndName[0];
			String name = pathAndName[1];
			
			ObjectMetadata omd = objectFileManager.unique(path, false, e->{
				return 
						name.equals(e.getId()) && 
						(
							(locale == null && e.getLocale() == null) || 
							(locale != null && locale.equals(e.getLocale()))
						);
			});
			
			if(omd == null) {
				return;
			}

			objectListenerManager.beforeUnregister(id, locale);

			ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
			
			if(map != null) {
				map.remove(getSafeLocale(locale));
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
	public void registerDriver(String name, ObjectsManagerDriver driver) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".driver.register"));
		}
		
		drivers.putIfAbsent(name, driver);
	}

	@Override
	public void unregisterDriver(String name, ObjectsManagerDriver driver) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".driver.unregister"));
		}
		
		drivers.remove(name, driver);
	}

	@Override
	public Object getObject(String id) {
		return getObject(id, null);
	}

	@Override
	public Object getObject(String id, Locale locale) {
		
		if(!isVaidID(id)) {
			throw new IllegalStateException("invalid format: " + id);
		}
		
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
		
		if(!isVaidID(id)) {
			throw new IllegalStateException("invalid format: " + id);
		}
		
		readLock.lock();
		try {
			String[] pathAndName = toPathAndName(id);
			String path = pathAndName[0];
			String name = pathAndName[1];
			
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

	@Override
	public List<Object> list(String path, String name, Locale locale, boolean recursive) {
		
		readLock.lock();
		try {
			List<ObjectMetadata> list = objectFileManager.list(path, recursive, e->{
				boolean result;
				result =           (name == null? true : e.getId().contains(name));
				result = result && (locale == null? true : locale.equals(e.getLocale()));
				return result;
			});
			
			List<Object> r = new ArrayList<Object>();
			for(ObjectMetadata omd: list) {
				Object o = get(omd.getPath() + "/" + omd.getId(), omd.getLocale());
				if(o != null) {
					r.add(o);
				}
			}
			return r;
		}
		finally {
			readLock.unlock();
		}
			
	}

	@Override
	public List<ObjectMetadata> listMetadata(String path, String name, Locale locale, boolean recursive) {
		
		readLock.lock();
		try {
			return objectFileManager.list(path, recursive, e->{
				boolean result;
				result =           (name == null? true : e.getId().contains(name));
				result = result && (locale == null? true : locale.equals(e.getLocale()));
				return result;
			});
			
		}
		finally {
			readLock.unlock();
		}
			
	}
	
	@Override
	public List<ObjectEntry> listObjects(String path, String name, boolean recursive) {
		
		readLock.lock();
		try {
			List<ObjectMetadata> list = objectFileManager.list(path, recursive, e->{
				return name == null? true : e.getId().contains(name);
			});
			
			Map<String, List<ObjectMetadata>> group = new HashMap<String, List<ObjectMetadata>>();
			
			for(ObjectMetadata omd: list) {
				String id = omd.getPath() + "/" + omd.getId();
				
				List<ObjectMetadata> g = group.get(id);
				
				if(g == null) {
					g = new ArrayList<ObjectMetadata>();
					group.put(id, g);
				}
				
				g.add(omd);
			}
			
			List<ObjectEntry> r = new ArrayList<ObjectEntry>();

			for(Entry<String,List<ObjectMetadata>> e: group.entrySet()) {
				
				Map<Locale,Object> map = new HashMap<Locale, Object>();
				
				for(ObjectMetadata omd: e.getValue()) {
					
					Object o = get(e.getKey(), omd.getLocale());
					
					if(o != null) {
						map.put(omd.getLocale(), o);
					}
				}
				
				if(!map.isEmpty()) {
					ObjectMetadata base = e.getValue().get(0);
					r.add(new ObjectEntry(base.getPath(), base.getId(), map));
				}
				
			}
			
			return r;
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
	
	public void flush() {
		writeLock.lock();
		try {
			objects.clear();
		}
		finally {
			writeLock.unlock();
		}		
	}

	/* --private-- */
	
	/* get */
	
	private Object get(String id, Locale locale) {
		
		ObjectValue obj;
		ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
		
		if(map != null) {
			obj = map.get(getSafeLocale(locale));
			
			if(obj != null && obj.isValid()) {
				return obj.getObject();
			}
		}
		
		try {
			obj = loadObject(id, locale);
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
		
		return obj == null? null : obj.getObject(); 
	}
	
	private synchronized ObjectValue loadObject(String id, Locale locale) throws IOException {
		
		ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
		
		if(map != null) {
			ObjectValue obj = map.get(getSafeLocale(locale));
			if(obj != null && obj.isValid()) {
				return obj;
			}
		}
		
		String[] pathAndName = toPathAndName(id);
		String path = pathAndName[0];
		String name = pathAndName[1];
		
		ObjectMetadata omd = objectFileManager.unique(path, false, e->{
			return 
					name.equals(e.getId()) && 
					(
						(locale == null && e.getLocale() == null) || 
						(locale != null && locale.equals(e.getLocale()))
					);
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
			map.put(getSafeLocale(locale), object);
			
			return object;
		}
		
		map = objects.get(id);
		
		if(map != null ) {
			map.remove(getSafeLocale(locale));
			if(map.isEmpty()) {
				objects.remove(id);
			}
		}
		
		return null;
	}
	
	/* private */
	
	private Locale getSafeLocale(Locale locale) {
		return locale == null? NULL_LOCALE : locale;
	}
	
	/* persist */
	
	private ObjectValue persistObject(String driverName, String path, String name, Locale locale, Object object) throws ObjectsManagerDriverException {
		
		ObjectsManagerDriver driver = drivers.get(driverName);
		
		if(driver == null) {
			throw new ObjectsManagerDriverException("not found: " + driverName);
		}
		
		return driver.persist(path, name, locale, object);
	}

	/* delete */
	
	private synchronized void deleteObject(ObjectMetadata omd) throws IOException {
		objectFileManager.delete(omd);
	}
	
	/* base */
	
	public static class ObjectFileMetadataManager extends FileMetadata{
		
		private ObjectHandler handler;
		
		public ObjectFileMetadataManager(FileMetadata fmd, ObjectHandler handler) {
			super(fmd);
			this.handler = handler;
		}

		public ObjectHandler getHandler() {
			return handler;
		}

	}
	
	private static class ObjectListenerManager implements ObjectListener{

		private List<ObjectListener> listeners;
		
		private ReadWriteLock readWriteLock;
		
		public ObjectListenerManager() {
			this.listeners = new LinkedList<ObjectListener>();
			this.readWriteLock = new ReentrantReadWriteLock();
		}
		
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
