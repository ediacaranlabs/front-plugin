package br.com.uoutec.community.ediacaran.front;

import br.com.uoutec.community.ediacaran.front.pub.AdminMenuBar;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.system.AbstractWebPluginInstaller;
import br.com.uoutec.community.ediacaran.system.pub.Menu;
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
	
	private static final String ADMIN_TOP_MENU_BAR      = "adminTopMenuBar";
	
	public void install() throws Throwable{
		super.install();
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);
		AdminMenuBar leftMenu = new AdminMenuBar(); 
		AdminMenuBar topMenu = new AdminMenuBar();
		
		mbm.registerMenuBar(ADMIN_MENU_BAR, leftMenu);
		mbm.registerMenuBar(ADMIN_TOP_MENU_BAR,topMenu);
		
		topMenu.addMenu(new Menu("Dashboard", "tachometer", "/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/dashboard", null, null, null, null, -1));
		
		if("true".equals(super.metadata.getValue("test").getValue())){
			
			topMenu.addMenu(new Menu("Components", "tree", "/plugins/ediacaran/front/admin/components.jsp", null, null, null, null, 1));
			topMenu.addMenu(new Menu("Forms", "edit", "/plugins/ediacaran/front/admin/form.jsp", null, null, null, null, 1));
			topMenu.addMenu(new Menu("Typography", "pencil", "/plugins/ediacaran/front/admin/typography.jsp", null, null, null, null, 1));
			topMenu.addMenu(new Menu("Tables", "table", "/plugins/ediacaran/front/admin/table.jsp", null, null, null, null, 1));
			topMenu.addMenu(new Menu("Pricing boxes", "money", "/plugins/ediacaran/front/admin/pricingbox.jsp", null, null, null, null, 1));
			topMenu.addMenu(new Menu("Flot Charts", "pie-chart", "/plugins/ediacaran/front/admin/flotcharts.jsp", null, null, null, null, 1));
			
		}
	}
	
	public void uninstall() throws Throwable {
		super.uninstall();
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);
		mbm.removeMenuBar(ADMIN_MENU_BAR);
		mbm.removeMenuBar(ADMIN_TOP_MENU_BAR);
	}
	
}
