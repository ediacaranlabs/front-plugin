package br.com.uoutec.community.ediacaran.front;

import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginProperties;

@Deprecated
public interface TemplateCache {

	void configure(PluginProperties config) throws PluginException;
	
	boolean contains(String name);
	
	void register(String name, StringPattern template);
	
	void remove(String name);
	
	StringPattern get(String name);
	
	void clear();
}
