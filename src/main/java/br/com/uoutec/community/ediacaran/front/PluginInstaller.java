package br.com.uoutec.community.ediacaran.front;

import org.brandao.brutos.ClassUtil;
import org.brandao.brutos.io.DefaultResourceLoader;

import br.com.uoutec.application.EntityContext;
import br.com.uoutec.application.se.ApplicationBootstrapProvider;
import br.com.uoutec.community.ediacaran.PluginManager;
import br.com.uoutec.community.ediacaran.ServerBootstrap;
import br.com.uoutec.community.ediacaran.front.tags.TemplateCache;
import br.com.uoutec.community.ediacaran.front.tags.TemplateLoader;
import br.com.uoutec.community.ediacaran.front.tags.TemplatesManager;
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
		
	}
	
	private void installTemplateManager() throws PluginException {
		PluginManager pm = EntityContext.getEntity(PluginManager.class);
		PluginMetadata pmd = pm.findById(PLUGIN);
		
		PluginPropertyValue cp = pmd.getValue(CACHE_PROVIDER_PROPERTY);
		
		TemplateCache tc = (TemplateCache) ClassUtil.getInstance(cp.getValue());
		
		PluginPropertyValue ppv = pmd.getValue(TEMPLATE_PROPERTY);
		String template = ppv.getValue();
		
		return "/plugins/community/ediacaran/front/" + template + "/" + type + ".jsp";
		
		TemplatesManager templatesManager = new TemplatesManager(
				new TemplateLoader(), new DefaultResourceLoader(), pmd.getPath().get, tc, "UTF-8");
	}
}
