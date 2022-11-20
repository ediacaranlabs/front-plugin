package br.com.uoutec.community.ediacaran.front.page;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import br.com.uoutec.community.ediacaran.front.objects.FileManager;
import br.com.uoutec.community.ediacaran.front.objects.FileObjectsManagerDriver;

public class PageObjectDriver
	extends FileObjectsManagerDriver
	implements ObjectsTemplateManagerDriver{

	private final ObjectTemplate defaultTemplate = 
			new ObjectTemplate(
					"default", 
					"Default Template", 
					"${plugins.ediacaran.front.web_path}:/pages/admin/edit.jsp", 
					"${plugins.ediacaran.front.web_path}:/pages/default-template.jsp"
			);
	
	private ConcurrentMap<String, ObjectTemplate> templates;
	
	public PageObjectDriver() {
		super(
				new FileManager(
						new File(System.getProperty("app.web")), 
						new PageFileManagerHandler()
				), "pages");
		
		this.templates = new ConcurrentHashMap<String, ObjectTemplate>();
		this.templates.put("defailt", defaultTemplate);
	}

	@Override
	public ObjectTemplate getTemplate(Object object) {
		
		Page page = (Page)object;
		
		ObjectTemplate template = templates.get(page.getTemplate());
		
		return templates == null? defaultTemplate : template;
	}

	@Override
	public List<ObjectTemplate> getTemplates() {
		return Collections.unmodifiableList( templates.values().stream().collect(Collectors.toList()) );
	}

	@Override
	public Map<String, ObjectTemplate> getTemplatesIdMap() {
		return Collections.unmodifiableMap(templates);
	}
	
}
