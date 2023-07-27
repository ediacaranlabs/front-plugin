package br.com.uoutec.community.ediacaran.front.theme;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.brandao.brutos.io.Resource;
import org.brandao.brutos.io.ResourceLoader;

import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PublicBean;

@SuppressWarnings("deprecation")
public class TemaLoaderImp implements TemaLoader, PublicBean{

	private File templatesPath;
	
	private ResourceLoader loader;
	
	@SuppressWarnings("unused")
	private TemplateLoader templateLoader;
	
	@SuppressWarnings("unused")
	private String charset;
	
	@SuppressWarnings("unused")
	private String context;
	
	@SuppressWarnings("unused")
	private String base;
	
	public TemaLoaderImp(File templatesPath, ResourceLoader loader, TemplateLoader templateLoader, String charset,
			String context, String base) {
		this.templatesPath = templatesPath;
		this.loader = loader;
		this.templateLoader = templateLoader;
		this.charset = charset;
		this.context = context;
		this.base = base;
	}

	@Override
	public Theme loadTema() {
		try {
			return null;//new TemaImp(getTagTemplates(), context, base);
		}
		catch(Throwable e) {
			throw new ThemeException(e);
		}
	}

	@SuppressWarnings("unused")
	private Map<String,ComponentTemplate> getTagTemplates() throws IOException, PluginException {
		
		Map<String,ComponentTemplate> templates = new HashMap<String, ComponentTemplate>();
		
		File[] files = templatesPath.listFiles();
		
		for(File f: files) {
			
			if(!f.getName().endsWith(".tmp")) {
				continue;
			}
			
			Resource r      = loader.getResource(f.toURI().toString());
			//TagTemplate t = templateLoader.load(r, charset);
			
			//templates.put(f.getName().split("\\.")[0], t);
						
		}
		
		return templates;
	}
	
}
