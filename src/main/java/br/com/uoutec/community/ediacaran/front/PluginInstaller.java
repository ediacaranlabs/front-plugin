package br.com.uoutec.community.ediacaran.front;

import java.io.IOException;

import org.brandao.brutos.io.DefaultResourceLoader;

import br.com.uoutec.application.ClassUtil;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginProperties;
import br.com.uoutec.community.ediacaran.plugins.PluginsSuppliers;
import br.com.uoutec.community.ediacaran.system.AbstractWebPluginInstaller;

public class PluginInstaller 
	extends AbstractWebPluginInstaller {

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
	
	@SuppressWarnings("unchecked")
	private void installTemplateManager() 
			throws PluginException, InstantiationException, IllegalAccessException, 
			ClassNotFoundException, IOException {
		
		PluginsSuppliers ps = EntityContextPlugin.getEntity(PluginsSuppliers.class);
		PluginProperties pp   = ps.getSupplier(PROVIDER).getProperties(PLUGIN);
		String cacheProvider  = pp.getString(CACHE_PROVIDER_PROPERTY);
		
		cacheProvider = cacheProvider == null? HashMapTemplateCache.class.getName() : cacheProvider;
		
		Class<? extends TemplateCache> cacheProviderClass = 
				(Class<? extends TemplateCache>) ClassUtil.get(cacheProvider);
		TemplateCache tc = EntityContextPlugin.getEntity(cacheProviderClass);
		tc.configure(pp);
		
		TemplatesManager templatesManager = new TemplatesManagerImp(
				new TemplateLoader(), new DefaultResourceLoader(), tc, pp, "UTF-8");
		
		TemplatesManagerProvider.setTemplatesManager(templatesManager);
	}
	
}
