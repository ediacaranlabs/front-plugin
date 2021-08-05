package br.com.uoutec.community.ediacaran.front;

import br.com.uoutec.community.ediacaran.AbstractPlugin;
import br.com.uoutec.community.ediacaran.EdiacaranListenerManager;
import br.com.uoutec.community.ediacaran.core.security.AuthenticationProvider;
import br.com.uoutec.community.ediacaran.core.security.SecurityManager;
import br.com.uoutec.community.ediacaran.front.pub.AdminMenuBar;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBarManager;
import br.com.uoutec.community.ediacaran.front.pub.MenuBarManagerException;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widget;
import br.com.uoutec.community.ediacaran.front.pub.widget.WidgetException;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widgets;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.security.pub.WebSecurityManagerPlugin;
import br.com.uoutec.community.ediacaran.security.pub.test.AuthenticationProviderImp;

public class PluginInstaller 
	extends AbstractPlugin {

	public static final String PACKAGE 					= "community";

	public static final String PROVIDER 				= "ediacaran";

	public static final String PLUGIN 					= "front";
	
	public static final String CACHE_SIZE_PROPERTY 		= "cache_size";
	
	public static final String CACHE_PROVIDER_PROPERTY 	= "cache_provider";
	
	public static final String TEMPLATE_PROPERTY 		= "template";
	
	private static final String ADMIN_MENU_BAR          = "adminMenuBar";
	
	private static final String ADMIN_TOP_MENU_BAR      = "adminTopMenuBar";
	
	public void install() throws Throwable{
		installMenu();
		installWidgets();
		installSecurityConfig();
		installListeners();
	}
	
	private void installMenu() throws MenuBarManagerException {
		
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);

		AdminMenuBar leftMenu = new AdminMenuBar(); 
		AdminMenuBar topMenu = new AdminMenuBar();
		
		mbm.registerMenuBar(ADMIN_MENU_BAR, leftMenu);
		mbm.registerMenuBar(ADMIN_TOP_MENU_BAR,topMenu);
		
		leftMenu.addMenu(
			new Menu("Dashboard")
				.setIcon("tachometer")
				.setResource("/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/dashboard")
				.setOrder(100)
		);
		
		if(!pluginConfiguration.getBoolean("test")){
			return;
		}
			
		topMenu.addMenu(
				new Menu("Messages")
					.setIcon("comments")
					.setBadge("3")
					.setBadgeStyle("danger")
					.setOrder(100)
		);
		
		topMenu.addMenu(
				new Menu("Notification")
					.setIcon("bell")
					.setBadge("15")
					.setBadgeStyle("warning")
					.setOrder(99)
		);
		
		leftMenu.addMenu(
				new Menu("Components")
					.setIcon("tree")
					.setResource("/plugins/ediacaran/front/admin/components.jsp")
					.setOrder(1)
		);
		
		leftMenu.addMenu(
				new Menu("Forms")
					.setIcon("edit")
					.setResource("/plugins/ediacaran/front/admin/form.jsp")
					.setOrder(1)
		);
		
		leftMenu.addMenu(
				new Menu("Typography")
					.setIcon("pencil")
					.setResource("/plugins/ediacaran/front/admin/typography.jsp")
					.setOrder(1)
		);
		
		leftMenu.addMenu(
				new Menu("Tables")
					.setIcon("table")
					.setResource("/plugins/ediacaran/front/admin/table.jsp")
					.setOrder(1)
		);
		
		leftMenu.addMenu(
				new Menu("Pricing boxes")
					.setIcon("money")
					.setResource("/plugins/ediacaran/front/admin/pricingbox.jsp")
					.setOrder(1)
		);
		
		leftMenu.addMenu(
				new Menu("Flot Charts")
					.setIcon("pie-chart")
					.setResource("/plugins/ediacaran/front/admin/flotcharts.jsp")
					.setOrder(1)
		);
		
		leftMenu.addMenu(
				new Menu("Plugins")
					.setIcon("pie-chart")
					.setResource("/plugins/ediacaran/front/admin/plugins")
					.setOrder(1)
		);
		
		leftMenu.addMenu(
				new Menu("Menu")
					.setIcon("tree")
					.setResource("#")
					.setOrder(1)
					.addItem(
							new Menu("Item 1")
								.setIcon("tree")
								.setResource("#")
								.setOrder(1)
					)
					.addItem(
							new Menu("Item 2")
								.setIcon("tree")
								.setResource("#")
								.setOrder(1)
					)
					.addItem(
							new Menu("Item 3")
								.setIcon("tree")
								.setResource("#")
								.setOrder(1)
					)
		);
		
	}
	
	private void installWidgets() throws WidgetException {
		Widgets widgets = EntityContextPlugin.getEntity(Widgets.class);
		
		if(pluginConfiguration.getBoolean("test")){
			widgets.addWidget(new Widget("w1", "/plugins/ediacaran/front/admin/widgets/w1.jsp", 100));
			widgets.addWidget(new Widget("w2", "/plugins/ediacaran/front/admin/widgets/w2.jsp", 100));
		}
		
	}

	private void installListeners() {
		
		EdiacaranListenerManager  ediacaranListenerManager = EntityContextPlugin.getEntity(EdiacaranListenerManager.class);
		ediacaranListenerManager.addListener(EntityContextPlugin.getEntity(ThemeEdiacaranListener.class));
		
	}
	
	private void installSecurityConfig() {
		
		WebSecurityManagerPlugin webSecurityManagerPlugin = 
				EntityContextPlugin.getEntity(WebSecurityManagerPlugin.class);
		
		webSecurityManagerPlugin
			.addConstraint("/admin/manager/*")
				.addRole("manager")
				.addRole("user")
			.addConstraint("/admin/*")
				.addRole("user")
			.form("/login", "/login?error=true");
		
		if(pluginConfiguration.getBoolean("test")){
			SecurityManager sm = EntityContextPlugin.getEntity(SecurityManager.class);
			AuthenticationProvider ap = EntityContextPlugin.getEntity(AuthenticationProviderImp.class);
			sm.registerAuthenticationProvider(ap);
		}
		
	}
	
	public void uninstall() throws Throwable {
		uninstallMenu();
		uninstallWidget();
		uninstallSecurityConfig();
		uninstallListeners();
	}

	private void uninstallSecurityConfig() {
	}
	
	public void uninstallMenu() throws Throwable {
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);
		mbm.removeMenuBar(ADMIN_MENU_BAR);
		mbm.removeMenuBar(ADMIN_TOP_MENU_BAR);
	}

	public void uninstallWidget() throws Throwable {
		Widgets widgets = EntityContextPlugin.getEntity(Widgets.class);
		
		if(pluginConfiguration.getBoolean("test")){
			widgets.removeWidget("w2");
			widgets.removeWidget("w1");
		}
		
	}
	
	private void uninstallListeners() {
		
		EdiacaranListenerManager ediacaranListenerManager = EntityContextPlugin.getEntity(EdiacaranListenerManager.class);
		ediacaranListenerManager.removeListener(EntityContextPlugin.getEntity(ThemeEdiacaranListener.class));
		
	}
	
}
