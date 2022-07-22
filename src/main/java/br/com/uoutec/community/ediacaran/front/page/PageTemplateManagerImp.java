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
public class PageTemplateManagerImp 
	implements PageTemplateManager, PublicBean{

	public static final String basePermission = "app.pages.template.";

	private ConcurrentMap<String, PageTemplate> map;
	
	public PageTemplateManagerImp() {
		this.map = new ConcurrentHashMap<String, PageTemplate>();
	}
	
	@Override
	public void registerTemplate(String id, String name, String formPath, String template) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + id + ".register"));
		}
		
		if(map.putIfAbsent(id, new PageTemplate(id, name, formPath, template)) != null) {
			throw new PageTemplateManagerException("has benn added: " + id);
		}
	}

	@Override
	public PageTemplate getTemplate(String id) {
		return map.get(id);
	}

	@Override
	public List<PageTemplate> getTemplates() {
		
		List<PageTemplate> r = new ArrayList<PageTemplate>();
		
		for(Entry<String,PageTemplate> e: map.entrySet()) {
			r.add(e.getValue());
		}
		
		return Collections.unmodifiableList(r);
	}

	public Map<String,PageTemplate> getTemplatesIdMap(){
		
		Map<String,PageTemplate> r = new HashMap<String, PageTemplate>();
		
		for(Entry<String,PageTemplate> e: map.entrySet()) {
			r.put(e.getKey(),e.getValue());
		}
		
		return Collections.unmodifiableMap(r);
	}
	
	@Override
	public void unregisterTemplate(String id) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + id + ".unregister"));
		}
		
		map.remove(id);
	}

}
