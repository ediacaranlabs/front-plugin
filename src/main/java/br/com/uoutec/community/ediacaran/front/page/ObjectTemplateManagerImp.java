package br.com.uoutec.community.ediacaran.front.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@Singleton
public class ObjectTemplateManagerImp 
	implements ObjectTemplateManager, PublicBean{

	public static final String basePermission = "app.objects.template.";

	private ConcurrentMap<String, ConcurrentMap<String, ObjectTemplate>> map;
	
	public ObjectTemplateManagerImp() {
		this.map = new ConcurrentHashMap<String, ConcurrentMap<String, ObjectTemplate>>();
	}
	
	@Override
	public void registerTemplate(String type, String id, String name, String formPath, String template) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + id + ".register"));
		}
		
		ConcurrentMap<String, ObjectTemplate> mapType = map.get(type);
		
		if(mapType == null) {
			ConcurrentMap<String, ObjectTemplate> emptyMapType = new ConcurrentHashMap<String, ObjectTemplate>();
			mapType = map.putIfAbsent(type, emptyMapType);
			if(mapType == null) {
				mapType = emptyMapType;
			}
		}
		
		if(mapType.putIfAbsent(id, new ObjectTemplate(id, name, formPath, template)) != null) {
			throw new PageTemplateManagerException("has been added: " + id);
		}
	}

	@Override
	public ObjectTemplate getTemplate(String type, String id) {
		ConcurrentMap<String, ObjectTemplate> mapType = map.get(type);
		return mapType == null? null : mapType.get(id);
	}

	@Override
	public List<ObjectTemplate> getTemplates(String type) {
		
		ConcurrentMap<String, ObjectTemplate> mapType = map.get(type);
		
		if(mapType == null) {
			return null;
		}
		
		List<ObjectTemplate> r = new ArrayList<ObjectTemplate>();
		
		for(Entry<String,ObjectTemplate> e: mapType.entrySet()) {
			r.add(e.getValue());
		}
		
		return Collections.unmodifiableList(r);
	}

	public Map<String,ObjectTemplate> getTemplatesIdMap(String type){
		
		ConcurrentMap<String, ObjectTemplate> mapType = map.get(type);
		
		if(mapType == null) {
			return null;
		}
		
		Map<String,ObjectTemplate> r = new HashMap<String, ObjectTemplate>();
		
		for(Entry<String,ObjectTemplate> e: mapType.entrySet()) {
			r.put(e.getKey(),e.getValue());
		}
		
		return Collections.unmodifiableMap(r);
	}
	
	@Override
	public void unregisterTemplate(String type, String id) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + id + ".unregister"));
		}
		

		ConcurrentMap<String, ObjectTemplate> mapType = map.get(type);
		
		if(mapType != null) {
			mapType.remove(id);
			
			if(mapType.isEmpty()) {
				map.remove(type, mapType);
			}
			
		}
		
		
	}

}
