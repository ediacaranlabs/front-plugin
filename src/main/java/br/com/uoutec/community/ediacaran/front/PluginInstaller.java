package br.com.uoutec.community.ediacaran.front;

import java.io.IOException;

import org.brandao.brutos.io.DefaultResourceLoader;

import br.com.uoutec.application.ClassUtil;
import br.com.uoutec.community.ediacaran.core.system.AbstractPluginInstaller;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginProperties;
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
		
		PluginsProperties pps = EntityContextPlugin.getEntity(PluginsProperties.class);
		PluginProperties pp   = pps.getPluginProperties(PLUGIN);
		String cacheProvider  = pp.getString(CACHE_PROVIDER_PROPERTY);
		
		cacheProvider = cacheProvider == null? HashMapTemplateCache.class.getName() : cacheProvider;
		
		Class<? extends TemplateCache> cacheProviderClass = 
				(Class<? extends TemplateCache>) ClassUtil.get(cacheProvider, getClass().getClassLoader());
		TemplateCache tc = EntityContextPlugin.getEntity(cacheProviderClass);//(TemplateCache) ClassUtil.getInstance(cacheProvider, getClass().getClassLoader());
		tc.configure(pp);
		
		TemplatesManager templatesManager = new TemplatesManagerImp(
				new TemplateLoader(), new DefaultResourceLoader(), tc, pp, "UTF-8");
		
		TemplatesManagerProvider.setTemplatesManager(templatesManager);
	}
	
}
