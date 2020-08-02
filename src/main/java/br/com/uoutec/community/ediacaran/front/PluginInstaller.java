package br.com.uoutec.community.ediacaran.front;

import java.io.IOException;

import org.brandao.brutos.ClassUtil;
import org.brandao.brutos.io.DefaultResourceLoader;

import br.com.uoutec.community.ediacaran.core.system.AbstractPluginInstaller;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginsProperties;

public class PluginInstaller extends AbstractPluginInstaller {

	public static final String PACKAGE 					= "community";

	public static final String PROVIDER 				= "ediacaran";

	public static final String PLUGIN 					= "front";
	
	public static final String CACHE_SIZE_PROPERTY 		= "cache_size";
	
	public static final String CACHE_PROVIDER_PROPERTY 	= "cache_provider";
	
	public static final String TEMPLATE_PROPERTY 		= "template";
	
	public void install() throws PluginException {
		try{
			this.installTemplateManager();
		}
		catch(Throwable e) {
			throw new PluginException(e);
		}
	}
	
	private void installTemplateManager() 
			throws PluginException, InstantiationException, IllegalAccessException, 
			ClassNotFoundException, IOException {
		
		PluginsProperties pp   = EntityContextPlugin.getEntity(PluginsProperties.class);
		String cacheProvider = pp.getString(PLUGIN, CACHE_PROVIDER_PROPERTY);
		cacheProvider = cacheProvider == null? HashMapTemplateCache.class.getName() : cacheProvider;
		
		TemplateCache tc = (TemplateCache) ClassUtil.getInstance(cacheProvider);
		tc.configure(pp);
		
		TemplatesManager templatesManager = new TemplatesManagerImp(
				new TemplateLoader(), new DefaultResourceLoader(), tc, pp, PLUGIN, "UTF-8");
		
		TemplatesManagerProvider.setTemplatesManager(templatesManager);
	}
	
}
