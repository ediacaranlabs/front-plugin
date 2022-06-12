package br.com.uoutec.community.ediacaran.front.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@Singleton
public class ObjectsManagerImp 
	implements ObjectsManager, PublicBean {

	public static final String basePermission = "app.objs";
	
	private String OBJECTS_REPOSITORY = "/objects";
	
	private Pattern keyFormat = Pattern.compile("(/[a-z]+(-[0-9a-z]))+");
	
	private Pattern typeFormat = Pattern.compile("[0-9a-z]{4,10}");
	
	private ConcurrentMap<String, ConcurrentMap<Locale,ObjectValue>> objects;
	
	private ReadWriteLock readWriteLock;
	
	private Lock writeLock;
	
	private Lock readLock;
	
	private List<ObjectHandler> handlers;
	
	private ObjectPersistenceUtil objectPathNormalizer;
	
	public ObjectsManagerImp() {
		handlers = new LinkedList<ObjectHandler>();
		objects = new ConcurrentHashMap<String, ConcurrentMap<Locale,ObjectValue>>();
		this.objectPathNormalizer = new ObjectPersistenceUtil();
		this.readWriteLock = new ReentrantReadWriteLock();
		this.writeLock = readWriteLock.writeLock();
		this.readLock = readWriteLock.readLock();
	}
	
	@Override
	public void registerObject(String id, Locale locale, Object object) {
		
		if(object == null) {
			throw new NullPointerException("object");
		}
		
		if(!keyFormat.matcher(id).matches()) {
			throw new IllegalStateException("invalid format: " + id);
		}

		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + "." + id.replace("/", ".") + ".register"));
		}

		writeLock.lock();
		try {
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
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
		finally {
			writeLock.unlock();
		}
		
		
	}

	@Override
	public void unregisterObject(String id, String type, Locale locale) {

		if(!keyFormat.matcher(id).matches()) {
			throw new IllegalStateException("invalid format: " + id);
		}

		if(!typeFormat.matcher(type).matches()) {
			throw new IllegalStateException("invalid type: " + type);
		}
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + "." + id.replace("/", ".") + ".unregister"));
		}
		
		writeLock.lock();
		try {
			ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
			ObjectHandler handler = getObjectHandler(type);
		
			deleteObject(id, locale, handler);
			
			if(map != null) {
				map.remove(locale);
				if(map.isEmpty()) {
					objects.remove(id, map);
				}
			}
			
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
	public Object getObject(String id, String type) {
		return getObject(id, type, null);
	}

	@Override
	public Object getObject(String id, String type, Locale locale) {
		
		readLock.lock();
		try {
			Object object = getInnerObject(id, type, locale);
			
			if(object == null && locale == null) {
				object = getInnerObject(id, type, null);
			}
			
			return object;
		}
		finally {
			readLock.unlock();
		}
		
	}
	
	/* --private-- */
	
	/* get */
	
	public Object getInnerObject(String id, String type, Locale locale) {
		
		ObjectValue obj;
		ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
		
		if(map != null) {
			obj = map.get(locale);
			
			if(obj != null && obj.isValid()) {
				return obj;
			}
		}
		else {
			map = new ConcurrentHashMap<Locale,ObjectValue>();
			ConcurrentMap<Locale,ObjectValue> current = objects.putIfAbsent(id, map);
			if(current != null) {
				map = current;
			}
		}
		
		ObjectHandler handler = getObjectHandler(type);
		
		try {
			obj = loadObject(id, locale, handler);
		}
		catch(IOException e) {
			throw new IllegalStateException(e);
		}
		
		return obj == null? null : obj.object; 
	}
	
	private synchronized ObjectValue loadObject(String id, Locale locale, ObjectHandler handler) throws IOException {
		
		ObjectValue obj;
		ConcurrentMap<Locale,ObjectValue> map = objects.get(id);
		
		if(map != null) {
			obj = map.get(locale);
			if(obj != null && obj.isValid()) {
				return obj;
			}
		}
		
		obj = loadPersistenceObject(id, locale, handler);
		
		if(obj != null) {
			map = new ConcurrentHashMap<Locale,ObjectValue>();
			ConcurrentMap<Locale,ObjectValue> current = objects.putIfAbsent(id, map);
			if(current != null) {
				map = current;
			}
			map.put(locale, obj);
		}
		else {
			map = objects.get(id);
			if(map != null ) {
				map.remove(locale);
				if(map.isEmpty()) {
					objects.remove(id);
				}
			}
		}
		
		return obj;
	}
	
	private ObjectValue loadPersistenceObject(String id, Locale locale, ObjectHandler handler) throws IOException {
		
		File objectFile = getFile(id, locale, handler.getType());
		
		if(!objectFile.exists() || !objectFile.canRead()) {
			return null;
		}

		Object object;
		
		try(FileInputStream stream = new FileInputStream(objectFile)){
			object = handler.getReader().read(new InputStreamReader(stream, StandardCharsets.UTF_8));
		}
		
		return object == null? null : new ObjectValue(objectFile, object);
	}
	
	/* persist */
	
	private synchronized ObjectValue persistObject(String id, Locale locale, Object obj, ObjectHandler hanlder) throws IOException {
		
		File basePath   = getBasePath();
		File objectFile = getFile(id, locale, hanlder.getType());
		
		if(!objectFile.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + objectFile);
		}
		
		ObjectHandler handler = getObjectHandler(obj);
		
		try(FileOutputStream stream = new FileOutputStream(objectFile)){
			handler.getWriter().write(obj, new OutputStreamWriter(stream, StandardCharsets.UTF_8));
		}
		
		return new ObjectValue(objectFile, obj);
	}

	/* delete */
	
	private synchronized void deleteObject(String id, Locale locale, ObjectHandler handler) throws IOException {
		
		File basePath   = getBasePath();
		File objectFile = getFile(id, locale, handler.getType());
		
		if(!objectFile.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + objectFile);
		}
		
		deleteObject(basePath, objectFile);
	}

	private void deleteObject(File base, File file) throws IOException {
		
		file.delete();
		
		File parent = file.getParentFile();
		
		if(parent.equals(base)) {
			return;
		}
		
		if(parent.listFiles(e->e.isFile()).length == 0) {
			deleteObject(base, parent);
		}
		
	}
	
	/* base */
	
	private File getBasePath() {
		String basePath = System.getProperty("app.base");
		return new File(basePath + OBJECTS_REPOSITORY);
	}
	
	private File getFile(String id, Locale locale, String type) throws IOException {
		
		String objectPathSTR = objectPathNormalizer.toPath(id, locale, type);
		File basePath        = getBasePath();
		File objectFile      = new File(basePath, objectPathSTR);
		
		if(!objectFile.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + objectFile);
		}
		
		return objectFile.exists() || objectFile.canRead()? objectFile : null;

	}
	
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
	
	private static class ObjectValue {
		
		public File file;
		
		public long lastModified;
		
		public Object object;

		public ObjectValue(File file, Object object) {
			this.file = file;
			this.lastModified = object == null? -1 : file.lastModified();
			this.object = object;
		}
		
		public boolean isValid() {
			return lastModified == file.lastModified();
		}
	}
	
}
