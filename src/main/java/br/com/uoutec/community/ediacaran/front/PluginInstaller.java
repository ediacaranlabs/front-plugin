package br.com.uoutec.community.ediacaran.front;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import br.com.uoutec.community.ediacaran.AbstractPlugin;
import br.com.uoutec.community.ediacaran.EdiacaranListenerManager;
import br.com.uoutec.community.ediacaran.front.UserEventListenerManager.UserEvent;
import br.com.uoutec.community.ediacaran.front.objects.FileManager;
import br.com.uoutec.community.ediacaran.front.objects.FileObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.objects.JsonFileManagerHandler;
import br.com.uoutec.community.ediacaran.front.objects.MenubarObjectHandler;
import br.com.uoutec.community.ediacaran.front.objects.ObjectHandler;
import br.com.uoutec.community.ediacaran.front.objects.ObjectHandlerImp;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectValue;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerDriver.ObjectsManagerDriverListener;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerDriverException;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
import br.com.uoutec.community.ediacaran.front.pub.MenuBarManagerException;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widget;
import br.com.uoutec.community.ediacaran.front.pub.widget.WidgetException;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widgets;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.security.pub.WebSecurityManagerPlugin;

public class PluginInstaller 
	extends AbstractPlugin {

	public static final String OBJECTS_REPOSITORY       = "objects/";
	
	public static final String PACKAGE 					= "community";

	public static final String PROVIDER 				= "ediacaran";

	public static final String PLUGIN 					= "front";
	
	public static final String CACHE_SIZE_PROPERTY 		= "cache_size";
	
	public static final String CACHE_PROVIDER_PROPERTY 	= "cache_provider";
	
	public static final String TEMPLATE_PROPERTY 		= "template";
	
	private static final String ADMIN_MENU_BAR          = "adminmenubar";
	
	private static final String ADMIN_TOP_MENU_BAR      = "admintopmenubar";

	private ObjectHandler menubarObjectHandler;
	
	private ObjectsManagerDriver globalDriver;
	
	public void install() throws Throwable{
		installMenu();
		installWidgets();
		installSecurityConfig();
		installListeners();
	}
	
	private void installMenu() throws MenuBarManagerException, ObjectsManagerDriverException {

		this.menubarObjectHandler = new MenubarObjectHandler();
		
		this.globalDriver = 
				new FileObjectsManagerDriver(
						new FileManager(
								new File(System.getProperty("app.base"), OBJECTS_REPOSITORY), 
								new JsonFileManagerHandler()
						), "global"
				); 

		this.globalDriver.registerObjectHandler(this.menubarObjectHandler);
		
		this.globalDriver.setDefaultObjectHandler(new ObjectHandlerImp());
		this.globalDriver.addListener(new ObjectsManagerDriverListener() {
			
			public void afterLoad(ObjectMetadata omd, ObjectValue obj) {
				
				if(obj == null) {
					return;
				}

				if(obj.getObject() instanceof MenuBar && omd.getPath().equals("/admin/menus")) {
					
					if(omd.getId().equals(ADMIN_MENU_BAR)) {
						installDefaultMenu((MenuBar)obj.getObject());
					}
					else
					if(omd.getId().equals(ADMIN_TOP_MENU_BAR)) {
						installDefaultTopMenu((MenuBar)obj.getObject());
					}
					
				}
				
			}
			
		});
		
		ObjectsManager objectsManager = EntityContextPlugin.getEntity(ObjectsManager.class);
		
		objectsManager.registerDriver(this.globalDriver);

	}
	
	private void installDefaultMenu(MenuBar leftMenu) {
		
		if(!pluginConfiguration.getBoolean("test")){
			return;
		}
		
		leftMenu.addMenu("components")
			.setName("Components")
			.setIcon("tree")
			.setResource("#!/plugins/ediacaran/front/admin/components.jsp")
			.setOrder(1);
		
		leftMenu.addMenu("forms")
			.setName("Forms")
			.setIcon("edit")
			.setResource("#!/plugins/ediacaran/front/admin/form.jsp")
			.setOrder(1);
		
		leftMenu.addMenu("typography")
			.setName("Typography")
			.setIcon("pencil")
			.setResource("#!/plugins/ediacaran/front/admin/typography.jsp")
			.setOrder(1);
		
		leftMenu.addMenu("tables")
			.setName("Tables")
			.setIcon("table")
			.setResource("#!/plugins/ediacaran/front/admin/table.jsp")
			.setOrder(1);
		
		leftMenu.addMenu("pricing_boxes")
			.setName("Pricing boxes")
			.setIcon("money")
			.setResource("#!/plugins/ediacaran/front/admin/pricingbox.jsp")
			.setOrder(1);
		
		leftMenu.addMenu("flot_charts")
			.setName("Flot Charts")
			.setIcon("pie-chart")
			.setResource("#!/plugins/ediacaran/front/admin/flotcharts.jsp")
			.setOrder(1);
		
		Menu menu = leftMenu.addMenu("menu")
			.setName("Menu")
			.setIcon("tree")
			.setResource("#")
			.setOrder(1);
		
		menu.addItem("item_1")
				.setName("Item 1")
				.setIcon("tree")
				.setResource("#")
				.setOrder(1);
		menu.addItem("item_2")
				.setName("Item 2")
				.setIcon("tree")
				.setResource("#")
				.setOrder(1);
		menu.addItem("item_3")
				.setName("Item 3")
				.setIcon("tree")
				.setResource("#")
				.setOrder(1);
		
	}
	
	private void installDefaultTopMenu(MenuBar topMenu) {
		
		if(!pluginConfiguration.getBoolean("test")){
			return;
		}
		
		topMenu.addMenu("messages")
			.setName("Messages")
			.setIcon("comments")
			.setBadgeStyle("danger")
			.setOrder(100);
		
		topMenu.addMenu("notification")
			.setName("Notification")
			.setIcon("bell")
			.setBadgeStyle("warning")
			.setOrder(99);
		
		Menu topMenu1 = topMenu.addMenu("menu")
				.setName("Menu")
				.setIcon("tree")
				.setResource("#")
				.setOrder(1);
			
		topMenu1.addItem("item_1")
					.setName("Item 1")
					.setIcon("tree")
					.setResource("#")
					.setOrder(1);
		topMenu1.addItem("item_2")
					.setName("Item 2")
					.setIcon("tree")
					.setResource("#")
					.setOrder(1);
		topMenu1.addItem("item_3")
					.setName("Item 3")
					.setIcon("tree")
					.setResource("#")
					.setOrder(1);
		
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
		
		/*
		if(pluginConfiguration.getBoolean("test")){
			AuthorizationManager sm = EntityContextPlugin.getEntity(AuthorizationManager.class);
			AuthenticationProvider ap = EntityContextPlugin.getEntity(AuthenticationProviderImp.class);
			sm.registerAuthenticationProvider(ap);
		}
		*/
		
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
		
		ObjectsManager objectsManager = EntityContextPlugin.getEntity(ObjectsManager.class);
		objectsManager.unregisterDriver(globalDriver);
		
		/*
		MenuBarManager mbm = EntityContextPlugin.getEntity(MenuBarManager.class);
		mbm.removeMenuBar(ADMIN_MENU_BAR);
		mbm.removeMenuBar(ADMIN_TOP_MENU_BAR);
		*/
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
	
	public static class MenuPropertyChangeListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
			if(evt.getSource() instanceof MenuBar && "menu".equals(evt.getPropertyName())){
				
				Menu newValue = (Menu) evt.getNewValue();
				
				if(newValue != null) {
					newValue.addPropertyChangeListener(new MenuPropertyChangeListener());
				}
			}
			else
			if(evt.getSource() instanceof Menu && "item".equals(evt.getPropertyName())){
				Menu newValue = (Menu) evt.getNewValue();
				
				if(newValue != null) {
					newValue.addPropertyChangeListener(new MenuPropertyChangeListener());
				}
				
			}
			else
			if(evt.getSource() instanceof Menu && "badge".equals(evt.getPropertyName())){
				
				String value = (String)evt.getNewValue();
				Menu menu    = (Menu) evt.getSource();
				
				UserEventListenerManager userEventListenerManager = 
						EntityContextPlugin.getEntity(UserEventListenerManager.class);
				
				if(userEventListenerManager != null) {
					userEventListenerManager
						.asyncFireEvent(new UserEvent(menu.getId(), "menu.badge.change", value));
				}
				
			}
			
		}
		
	}
}
