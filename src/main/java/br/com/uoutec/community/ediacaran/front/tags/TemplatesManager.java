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
import org.brandao.brutos.scanner.vfs.Vfs;

import br.com.uoutec.community.ediacaran.front.StringPattern;

public class TemplatesManager {

	private ConcurrentMap<String, StringPattern> defaultTemplates = new ConcurrentHashMap<String, StringPattern>();

	private ConcurrentMap<String, StringPattern> templates = new ConcurrentHashMap<String, StringPattern>();
	
	private TemplateLoader templateLoader;
	
	private ResourceLoader loader;
	
	private String charset;
	
	public TemplatesManager(TemplateLoader templateLoader, ResourceLoader loader, String charset) {
		this.templateLoader = templateLoader;
		this.loader = loader;
		this.charset = charset;
	}

	public void loadDefaultTemplates() throws IOException {
		
		TagTemplateSearch tts = new TagTemplateSearch(loader);
		List<Resource> l = tts.lisTagTemplates();
		
		for(Resource r: l) {
			String name     = Vfs.getRelativePath(r.getURL()).substring(TagTemplateSearch.PATH.length()).substring(0, 4);
			StringPattern t = templateLoader.load(r, charset);
			defaultTemplates.put(name, t);
		}
		
	}
	
	public void addtemplate(String name, String resource) throws IOException {
		Resource r = loader.getResource(resource);
		StringPattern t = templateLoader.load(r, charset);
		
		if(templates.containsKey(name)) {
			throw new IllegalStateException("has been added: " + name);
		}
		
		templates.put(name, t);
	}

	public StringPattern getTemplate(String name) {
		StringPattern p = templates.get(name);
		return p == null? defaultTemplates.get(name) : p;
	}
	
	public void apply(String name, Map<String,Object> vars, Writer out) throws IOException {
		StringPattern p = getTemplate(name);
		
		if(p == null) {
			throw new IllegalStateException("template not found: " + name);
		}
		
		p.toWriter(out, vars);
	}
	
	public void removetemplate(String name){
		templates.remove(name);
	}

	private static TemplatesManager templatesManager;
	
	static {
		templatesManager = new TemplatesManager(new TemplateLoader(), new DefaultResourceLoader(), "UTF-8");
	}

	public static void setTemplatesManager(TemplatesManager value) {
		templatesManager = value;
	}
	
	public static TemplatesManager getTemplatesManager() {
		return templatesManager;
	}
	
}
