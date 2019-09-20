package br.com.uoutec.community.ediacaran.front;

import java.io.IOException;

import org.brandao.brutos.ClassUtil;
import org.brandao.brutos.io.DefaultResourceLoader;

import br.com.uoutec.application.EntityContext;
import br.com.uoutec.community.ediacaran.PluginManager;
import br.com.uoutec.community.ediacaran.plugins.PluginException;
import br.com.uoutec.community.ediacaran.plugins.PluginMetadata;
import br.com.uoutec.community.ediacaran.plugins.PluginPropertyValue;
import br.com.uoutec.community.ediacaran.system.AbstractPluginInstaller;

public class PluginInstaller extends AbstractPluginInstaller {

	public static final String PACKAGE = "community";

	public static final String PROVIDER = "ediacaran";

	public static final String PLUGIN = "front";
	
	public static final String CACHE_SIZE_PROPERTY = "cache_size";
	
	public static final String CACHE_PROVIDER_PROPERTY = "cache_provider";
	
	public static final String TEMPLATE_PROPERTY = "template";
	
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
		
		PluginManager pm   = EntityContext.getEntity(PluginManager.class);
		PluginMetadata pmd = pm.findById(PLUGIN);
		
		PluginPropertyValue cp = pmd.getValue(CACHE_PROVIDER_PROPERTY);
		
		TemplateCache tc = (TemplateCache) ClassUtil.getInstance(cp.getValue());
		
		TemplatesManager templatesManager = new TemplatesManagerImp(
				new TemplateLoader(), new DefaultResourceLoader(), tc, "UTF-8");
		
		TemplatesManagerProvider.setTemplatesManager(templatesManager);
	}
	
}
