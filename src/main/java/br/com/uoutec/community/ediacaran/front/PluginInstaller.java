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
		
		//<ec:menu-item href="#"><ec:icon icon="tachometer" size="1"/> <p>Dashboard</p></ec:menu-item>
		
		if("true".equals(super.metadata.getValue("test").getValue())){
			
			topMenu.add
			/*
		 	<ec:menu-item href="#!/plugins/ediacaran/front/admin/components.jsp"><ec:icon icon="tree" size="1"/> <p>Components</p></ec:menu-item>
			<ec:menu-item href="#!/plugins/ediacaran/front/admin/form.jsp"><ec:icon icon="edit" size="1"/> <p>Forms</p></ec:menu-item>
			<ec:menu-item href="#!/plugins/ediacaran/front/admin/typography.jsp"><ec:icon icon="pencil" size="1"/> <p>Typography</p></ec:menu-item>
			<ec:menu-item href="#!/plugins/ediacaran/front/admin/table.jsp"><ec:icon icon="table" size="1"/> <p>Tables</p></ec:menu-item>
			<ec:menu-item href="#!/plugins/ediacaran/front/admin/pricingbox.jsp"><ec:icon icon="money" size="1"/> <p>Pricing boxes</p></ec:menu-item>
			<ec:menu-item href="#!/plugins/ediacaran/front/admin/flotcharts.jsp"><ec:icon icon="pie-chart" size="1"/> <p>Flot Charts</p></ec:menu-item>
			*/
			
		}
	}
	
	public void uninstall() throws Throwable {
		super.uninstall();
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);
		mbm.removeMenuBar(ADMIN_MENU_BAR);
		mbm.removeMenuBar(ADMIN_TOP_MENU_BAR);
	}
	
}
