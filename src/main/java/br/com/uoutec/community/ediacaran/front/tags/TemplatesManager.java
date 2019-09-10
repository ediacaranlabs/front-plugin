package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.brandao.brutos.io.DefaultResourceLoader;
import org.brandao.brutos.io.Resource;
import org.brandao.brutos.io.ResourceLoader;

import br.com.uoutec.community.ediacaran.front.StringPattern;

public class TemplatesManager {

	private ConcurrentMap<String, StringPattern> defaultTemplates = new ConcurrentHashMap<String, StringPattern>();

	private ConcurrentMap<String, StringPattern> templates = new ConcurrentHashMap<String, StringPattern>();
	
	private TemplateLoader templateLoader;
	
	private ResourceLoader loader;
	
	private String charset;
	
	public TemplatesManager(TemplateLoader templateLoader, ResourceLoader loader, String charset) throws IOException {
		this.templateLoader = templateLoader;
		this.loader = loader;
		this.charset = charset;
		loadDefaultTemplates();
	}

	public void loadDefaultTemplates() throws IOException {
		
		TagTemplateSearch tts = new TagTemplateSearch(loader);
		List<Resource> l = tts.lisTagTemplates();
		
		for(Resource r: l) {
			String name     = r.getName();
			name            = r.getName().substring(TagTemplateSearch.PATH.length(), name.length() - 4);
			StringPattern t = templateLoader.load(r, charset);
			defaultTemplates.put(name, t);
		}
		
	}
	
	public void addtemplate(String name, String resource) throws IOException {
		
		Resource r      = loader.getResource(resource);
		StringPattern t = templateLoader.load(r, charset);
		
		if(t == null) {
			throw new IllegalStateException("template not found: " + resource);
		}
		
		if(templates.containsKey(name)) {
			throw new IllegalStateException("has been added: " + name);
		}
		
		templates.put(name, t);
	}

	public StringPattern getTemplate(String template) {
		StringPattern p = templates.get(template);
		return p == null? defaultTemplates.get(template) : p;
	}
	
	public void apply(String template, Map<String,Object> vars, Writer out) throws IOException {
		
		StringPattern p = getTemplate(template);
		
		if(p == null) {
			synchronized(TemplatesManager.class) {
				addtemplate(template, template);
			}
		}
		
		p.toWriter(out, vars);
	}

	public void apply(String template, Writer out, Object ... vars) throws IOException {
		
		StringPattern p = getTemplate(template);
		
		if(p == null) {
			synchronized(TemplatesManager.class) {
				addtemplate(template, template);
			}
		}
		
		p.toWriter(out, vars);
	}
	
	public void removetemplate(String name){
		templates.remove(name);
	}

	private static TemplatesManager templatesManager;
	
	static {
		try {
			templatesManager = new TemplatesManager(new TemplateLoader(), new DefaultResourceLoader(), "UTF-8");
		}
		catch(Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static void setTemplatesManager(TemplatesManager value) {
		templatesManager = value;
	}
	
	public static TemplatesManager getTemplatesManager() {
		return templatesManager;
	}
	
}
