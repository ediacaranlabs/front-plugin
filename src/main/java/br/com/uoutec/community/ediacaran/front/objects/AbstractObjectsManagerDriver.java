package br.com.uoutec.community.ediacaran.front.objects;

import java.util.List;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.Filter;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;

public abstract class AbstractObjectsManagerDriver implements ObjectsManagerDriver {

	public static final String basePermission = "app.objs";
	
	private String name;
	
	private List<ObjectHandler> handlers;
	
	public AbstractObjectsManagerDriver(String name) {
		this.name = name;
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
		
		handlers.add(handler);
	}

	@Override
	public void unregisterObjectHandler(ObjectHandler handler) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".handler.unregister"));
		}
		
		handlers.remove(handler);
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
		
		for(ObjectHandler handler: handlers) {
			if(handler.accept(obj)) {
				return handler;
			}
		}
		
		throw new ObjectsManagerDriverException("handler not found: " + obj);
	}

	protected ObjectHandler getObjectHandler(String type) throws ObjectsManagerDriverException {
		
		for(ObjectHandler handler: handlers) {
			if(handler.accept(type)) {
				return handler;
			}
		}
		
		throw new ObjectsManagerDriverException("handler not found: " + type);
	}
	
}
