package br.com.uoutec.community.ediacaran.front.tema;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.brandao.brutos.io.Resource;
import org.brandao.brutos.io.ResourceLoader;

import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PublicBean;

public class TemaLoaderImp implements TemaLoader, PublicBean{

	private File templatesPath;
	
	private ResourceLoader loader;
	
	private TemplateLoader templateLoader;
	
	private String charset;
	
	private String context;
	
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
	public Tema loadTema() {
		try {
			return new TemaImp(getTagTemplates(), context, base);
		}
		catch(Throwable e) {
			throw new TemaException(e);
		}
	}

	private Map<String,StringPattern> getTagTemplates() throws IOException, PluginException {
		
		Map<String,StringPattern> templates = new HashMap<String, StringPattern>();
		
		File[] files = templatesPath.listFiles();
		
		for(File f: files) {
			
			if(!f.getName().endsWith(".tmp")) {
				continue;
			}
			
			Resource r      = loader.getResource(f.toURI().toString());
			StringPattern t = templateLoader.load(r, charset);
			
			templates.put(f.getName().split("\\.")[0], t);
						
		}
		
		return templates;
	}
	
}
