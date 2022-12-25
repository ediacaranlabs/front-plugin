package br.com.uoutec.community.ediacaran.front.objects;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import br.com.uoutec.community.ediacaran.front.page.ObjectTemplate;
import br.com.uoutec.community.ediacaran.front.page.ObjectsTemplateManagerDriver;

public abstract class FileObjectsTemplateManagerDriver 
	extends FileObjectsManagerDriver
	implements ObjectsTemplateManagerDriver {

	private ConcurrentMap<String, ObjectTemplate> templates;
	
	public FileObjectsTemplateManagerDriver(FileManager fileManager, String name) {
		super(fileManager, name);
		this.templates = new ConcurrentHashMap<String, ObjectTemplate>();
	}

	@Override
	public void registerTemplate(ObjectTemplate template) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".templates.register"));
		}
		
		templates.put(template.getId(), template);
	}

	@Override
	public void unregisterTemplate(String id) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + ".templates.unregister"));
		}
		
		templates.remove(id);
		
	}
	
	@Override
	public ObjectTemplate getTemplateByName(String id) {
		return templates.get(id);
	}

	@Override
	public List<ObjectTemplate> getTemplates() {
		return  Collections.unmodifiableList(templates.values().stream()
				.collect(Collectors.toList()));
	}

	@Override
	public Map<String, ObjectTemplate> getTemplatesIdMap() {
		return Collections.unmodifiableMap(templates);
	}

}
