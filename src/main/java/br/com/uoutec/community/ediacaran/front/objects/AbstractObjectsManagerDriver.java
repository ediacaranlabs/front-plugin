package br.com.uoutec.community.ediacaran.front.objects;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.Filter;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;

public abstract class AbstractObjectsManagerDriver implements ObjectsManagerDriver {

	public static final String basePermission = "app.objs";
	
	private String name;
	
	private List<ObjectHandler> handlers;
	
	private ObjectHandler defaultObjectHandler;
	
	private ReadWriteLock handlersLock;
	
	public AbstractObjectsManagerDriver(String name) {
		this.name = name;
		this.handlersLock = new ReentrantReadWriteLock();
		this.handlers = new LinkedList<ObjectHandler>();
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void registerObjectHandler(ObjectHandler handler) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".handler.register"));
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
			sm.checkPermission(new RuntimePermission(basePermission + ".handler.unregister"));
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
			sm.checkPermission(new RuntimePermission(basePermission + ".default_handler.register"));
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
	
}
