package br.com.uoutec.community.ediacaran.front;

import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.system.AbstractWebPluginInstaller;
import br.com.uoutec.community.ediacaran.system.pub.MenuBarManager;

public class PluginInstaller 
	extends AbstractWebPluginInstaller {

	public static final String PACKAGE 					= "community";

	public static final String PROVIDER 				= "ediacaran";

	public static final String PLUGIN 					= "front";
	
	public static final String CACHE_SIZE_PROPERTY 		= "cache_size";
	
	public static final String CACHE_PROVIDER_PROPERTY 	= "cache_provider";
	
	public static final String TEMPLATE_PROPERTY 		= "template";
	
	private static final String ADMIN_MENU_BAR          = "adminMenuBar";
	
	public void install() throws Throwable{
		super.install();
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);
		mbm.registerMenuBar(ADMIN_MENU_BAR, new AdminMenuBar());
	}
	
	public void uninstall() throws Throwable {
		super.uninstall();
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);
		mbm.removeMenuBar(ADMIN_MENU_BAR);
	}
	
}
