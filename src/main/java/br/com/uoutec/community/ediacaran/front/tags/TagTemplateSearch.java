package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.brandao.brutos.io.Resource;
import org.brandao.brutos.io.ResourceLoader;

import br.com.uoutec.application.scanner.vfs.Dir;
import br.com.uoutec.application.scanner.vfs.Vfs;

public class TagTemplateSearch {

	public static final String PATH = "META-INF/ediacaran/tags";
	
	private ClassLoader classLoader;
	
	private ResourceLoader loader;
	
	public TagTemplateSearch(ResourceLoader loader) {
		this.loader = loader;
		this.classLoader = loader.getClassloader();
	}
	
	public List<Resource> lisTagTemplates(){
		try {
			
			Enumeration<URL> plugins = classLoader.getResources(PATH);
			List<Resource> templates = new ArrayList<Resource>();
			
			while(plugins.hasMoreElements()) {
				
				URL url = plugins.nextElement();
				search(Vfs.getDir(url), templates);
			}
			
			return templates;
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}
	
	private void search(Dir origin, List<Resource> templates) throws IOException {
		
		br.com.uoutec.application.scanner.vfs.File[] files = origin.getFiles();
		
		for(br.com.uoutec.application.scanner.vfs.File f: files) {
			
			if(f.isDirectory()) {
				search(Vfs.getDir(f.toURL()), templates);
			}
			else {
				templates.add(loader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + PATH + "/" + f.getRelativePath()));
			}
			
		}
	}
	
}

