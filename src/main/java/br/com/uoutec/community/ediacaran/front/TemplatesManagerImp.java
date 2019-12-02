package br.com.uoutec.community.ediacaran.front;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.brandao.brutos.io.Resource;
import org.brandao.brutos.io.ResourceLoader;

import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginMetadata;
import br.com.uoutec.community.ediacaran.plugins.PluginPropertyValue;

public class TemplatesManagerImp implements TemplatesManager {

	private PluginMetadata pluginMetadata;
	
	private TemplateCache cache;
	
	private TemplateLoader templateLoader;
	
	private ResourceLoader loader;
	
	private String charset;
	
	public TemplatesManagerImp(TemplateLoader templateLoader, ResourceLoader loader, 
			TemplateCache cache, PluginMetadata pluginMetadata, String charset) throws IOException {
		this.templateLoader = templateLoader;
		this.loader = loader;
		this.charset = charset;
		this.cache = cache;
		this.pluginMetadata = pluginMetadata;
	}

	protected StringPattern addtemplate(String name, String resource) throws IOException, PluginException {
		
		File file = new File(pluginMetadata.getPath().getBase() + "/tags" + "/" + resource + ".tmp");
		
		file = file.getCanonicalFile();
		
		Resource r      = loader.getResource(file.toURI().toString());
		StringPattern t = templateLoader.load(r, charset);
		
		if(t == null) {
			throw new IllegalStateException("template not found: " + resource);
		}
		
		if(cache.contains(name)) {
			throw new IllegalStateException("has been added: " + name);
		}
		
		cache.register(name, t);
		return t;
	}

	public StringPattern getTemplate(String template) {
		return cache.get(template);
	}
	
	public void apply(String template, Map<String,Object> vars, Writer out) throws TemplatesManagerException {
		
		try {
			
			PluginPropertyValue ppv = pluginMetadata.getValue(PluginInstaller.TEMPLATE_PROPERTY);
			String fullTemplate     = ppv.getValue() + template;
			
			StringPattern p = getTemplate(fullTemplate);
			
			if(p == null) {
				synchronized(TemplatesManager.class) {
					p = addtemplate(fullTemplate, fullTemplate);
				}
			}
			
			p.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemplatesManagerException(e);
		}
			
	}

	public void apply(String template, Writer out, Object ... vars) throws TemplatesManagerException {
		
		try {
			PluginPropertyValue ppv = pluginMetadata.getValue(PluginInstaller.TEMPLATE_PROPERTY);
			String fullTemplate     = ppv.getValue() + template;
			
			StringPattern p = getTemplate(fullTemplate);
			
			if(p == null) {
				synchronized(TemplatesManager.class) {
					p = addtemplate(fullTemplate, fullTemplate);
				}
			}
			
			p.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemplatesManagerException(e);
		}
	}
	
	public void removetemplate(String name){
		cache.remove(name);
	}

	public TemplateLoader getTemplateLoader() {
		return templateLoader;
	}

	public ResourceLoader getLoader() {
		return loader;
	}

	public String getCharset() {
		return charset;
	}

}
