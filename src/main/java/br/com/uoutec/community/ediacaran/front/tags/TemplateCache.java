package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.StringPattern;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginMetadata;

public interface TemplateCache {

	void configure(PluginMetadata config) throws PluginException;
	
	boolean contains(String name);
	
	void register(String name, StringPattern template);
	
	void remove(String name);
	
	StringPattern get(String name);
	
	void clear();
}
