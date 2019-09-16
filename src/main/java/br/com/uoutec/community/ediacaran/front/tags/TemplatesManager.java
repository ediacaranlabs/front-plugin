package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.brandao.brutos.io.Resource;
import org.brandao.brutos.io.ResourceLoader;

import br.com.uoutec.application.EntityContext;
import br.com.uoutec.community.ediacaran.PluginManager;
import br.com.uoutec.community.ediacaran.front.PluginInstaller;
import br.com.uoutec.community.ediacaran.front.StringPattern;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginMetadata;
import br.com.uoutec.community.ediacaran.plugins.PluginPropertyValue;

public class TemplatesManager {

	//private ConcurrentMap<String, StringPattern> defaultTemplates = new ConcurrentHashMap<String, StringPattern>();

	//private ConcurrentMap<String, StringPattern> templates = new ConcurrentHashMap<String, StringPattern>();
	
	private TemplateCache cache;
	
	private TemplateLoader templateLoader;
	
	private ResourceLoader loader;
	
	private String charset;
	
	public TemplatesManager(TemplateLoader templateLoader, ResourceLoader loader, TemplateCache cache, String charset) throws IOException {
		this.templateLoader = templateLoader;
		this.loader = loader;
		this.charset = charset;
		this.cache = cache;
		//loadDefaultTemplates();
	}

	/*
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
	*/
	
	protected void addtemplate(String name, String resource) throws IOException, PluginException {
		
		PluginManager pm = EntityContext.getEntity(PluginManager.class);
		PluginMetadata pmd = pm.findById(PluginInstaller.PLUGIN);
		
		PluginPropertyValue ppv = pmd.getValue(PluginInstaller.TEMPLATE_PROPERTY);
		String template = ppv.getValue();
		
		String pr = "file://" + pmd.getPath().getBase() + "/tags" + "/" + template + "/" + resource;
		
		Resource r      = loader.getResource(pr);
		StringPattern t = templateLoader.load(r, charset);
		
		if(t == null) {
			throw new IllegalStateException("template not found: " + resource);
		}
		
		if(cache.contains(name)) {
			throw new IllegalStateException("has been added: " + name);
		}
		
		cache.register(name, t);
	}

	public StringPattern getTemplate(String template) {
		return cache.get(template);
	}
	
	public void apply(String template, Map<String,Object> vars, Writer out) throws IOException, PluginException {
		
		StringPattern p = getTemplate(template);
		
		if(p == null) {
			synchronized(TemplatesManager.class) {
				addtemplate(template, template);
			}
		}
		
		p.toWriter(out, vars);
	}

	public void apply(String template, Writer out, Object ... vars) throws IOException, PluginException {
		
		StringPattern p = getTemplate(template);
		
		if(p == null) {
			synchronized(TemplatesManager.class) {
				addtemplate(template, template);
			}
		}
		
		p.toWriter(out, vars);
	}
	
	public void removetemplate(String name){
		cache.remove(name);
	}

	private static TemplatesManager templatesManager;
	
	/*
	static {
		try {
			templatesManager = new TemplatesManager(new TemplateLoader(), new DefaultResourceLoader(), "UTF-8");
		}
		catch(Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	*/
	
	public TemplateLoader getTemplateLoader() {
		return templateLoader;
	}

	public ResourceLoader getLoader() {
		return loader;
	}

	public String getCharset() {
		return charset;
	}

	public static void setTemplatesManager(TemplatesManager value) {
		templatesManager = value;
	}
	
	public static TemplatesManager getTemplatesManager() {
		return templatesManager;
	}
	
}
