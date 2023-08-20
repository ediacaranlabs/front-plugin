package br.com.uoutec.community.ediacaran.front;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import br.com.uoutec.community.ediacaran.AbstractPlugin;
import br.com.uoutec.community.ediacaran.EdiacaranListenerManager;
import br.com.uoutec.community.ediacaran.front.UserEventListenerManager.UserEvent;
import br.com.uoutec.community.ediacaran.front.objects.MenubarObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.objects.PageObjectTemplateType;
import br.com.uoutec.community.ediacaran.front.objects.PagesObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
import br.com.uoutec.community.ediacaran.front.pub.MenuBarManagerException;
import br.com.uoutec.community.ediacaran.front.security.pub.WebSecurityManagerPlugin;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.security.Authorization;
import br.com.uoutec.community.ediacaran.security.SecurityRegistry;
import br.com.uoutec.community.ediacaran.system.repository.FileManager;
import br.com.uoutec.community.ediacaran.system.repository.FileObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.JsonFileManagerHandler;
import br.com.uoutec.community.ediacaran.system.repository.ObjectHandlerImp;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManager;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManagerDriverException;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsTemplateManager;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsTemplateManagerDriver;
import br.com.uoutec.entity.registry.RegistryException;

public class PluginInstaller 
	extends AbstractPlugin {

	public static final String OBJECTS_REPOSITORY       = "objects/";

	public static final String PACKAGE 					= "community";

	public static final String PROVIDER 				= "ediacaran";

	public static final String PLUGIN 					= "front";
	
	public static final String CACHE_SIZE_PROPERTY 		= "cache_size";
	
	public static final String CACHE_PROVIDER_PROPERTY 	= "cache_provider";
	
	public static final String TEMPLATE_PROPERTY 		= "template";
	
	private ObjectsManagerDriver globalDriver;
	
	private ObjectsTemplateManagerDriver pageObjectDriver;

	private MenubarObjectsManagerDriver menubarObjectDriver;
	
	public void install() throws Throwable{
		installPageTemplates();
		installMenu();
		installSecurityConfig();
		installListeners();
	}
	
	private void installPageTemplates() {
	}

	private void installMenu() throws MenuBarManagerException, ObjectsManagerDriverException {

		/* Global Object Driver */
		
		this.globalDriver = 
				new FileObjectsManagerDriver(
						new FileManager(
								new File(System.getProperty("app.base"), OBJECTS_REPOSITORY), 
								new JsonFileManagerHandler()
						), "global"
				); 

		this.globalDriver.setDefaultObjectHandler(new ObjectHandlerImp());
		
		/* Menu Object Driver */
		
		this.menubarObjectDriver = new MenubarObjectsManagerDriver();
		
		/* Page Object Driver */
		
		pageObjectDriver = new PagesObjectsManagerDriver();

		ObjectsTemplateManager objectsManager = EntityContextPlugin.getEntity(ObjectsTemplateManager.class);
		
		objectsManager.registerDriver(this.globalDriver);
		objectsManager.registerDriver(this.pageObjectDriver);
		objectsManager.registerDriver(this.menubarObjectDriver);

		/* Page Templates */
		
		PageManager editPage = EntityContextPlugin.getEntity(PageManager.class);
		
		editPage
			.registerTemplate(
					"default", 
					"Default Template", 
					"${plugins.ediacaran.front.web_path}:/templates/default_template/pages/default-template.jsp", 
					PageObjectTemplateType.VIEW
			);
		
		editPage
			.registerTemplate(
					"default", 
					"Default Template", 
					"${plugins.ediacaran.front.web_path}:/admin/pages/edit.jsp", 
					PageObjectTemplateType.FORM
			);
		
	}

	private void installListeners() {
		EdiacaranListenerManager  ediacaranListenerManager = EntityContextPlugin.getEntity(EdiacaranListenerManager.class);
		ediacaranListenerManager.addListener(EntityContextPlugin.getEntity(ThemeEdiacaranListener.class));
		ediacaranListenerManager.addListener(EntityContextPlugin.getEntity(LanguageRequestListener.class));
	}
	
	private void installSecurityConfig() throws RegistryException {
		
		SecurityRegistry securityRegistry = EntityContextPlugin.getEntity(SecurityRegistry.class);
		
		securityRegistry.registerAuthorization(new Authorization("CONTENT","Content","Content Manager"));
		
		securityRegistry.registerAuthorization(new Authorization("PAGES"  ,"Pages","Page Manager"),  "CONTENT");
		securityRegistry.registerAuthorization(new Authorization("LIST"   ,"List","List Pages"),     "CONTENT", "PAGES");
		securityRegistry.registerAuthorization(new Authorization("SAVE"   ,"Save","Save Pages"),     "CONTENT", "PAGES");
		securityRegistry.registerAuthorization(new Authorization("UPDATE" ,"Update","Update Pages"), "CONTENT", "PAGES");
		securityRegistry.registerAuthorization(new Authorization("DELETE" ,"Delete","Delete Pages"), "CONTENT", "PAGES");
		
		securityRegistry.registerAuthorization(new Authorization("MENUBAR","Menubar","Menubar Manager"), "CONTENT");
		securityRegistry.registerAuthorization(new Authorization("LIST"   ,"List","List Menubar"),       "CONTENT", "MENUBAR");
		securityRegistry.registerAuthorization(new Authorization("SAVE"   ,"Save","Save Menubar"),       "CONTENT", "MENUBAR");
		securityRegistry.registerAuthorization(new Authorization("UPDATE" ,"Update","Update Menubar"),   "CONTENT", "MENUBAR");
		securityRegistry.registerAuthorization(new Authorization("DELETE" ,"Delete","Delete Menubar"),   "CONTENT", "MENUBAR");
		
		WebSecurityManagerPlugin webSecurityManagerPlugin = 
				EntityContextPlugin.getEntity(WebSecurityManagerPlugin.class);
	
		webSecurityManagerPlugin
			.addConstraint("/admin/manager/*")
				.addRole("manager")
				.addRole("user")
			.addConstraint("/admin/*")
				.addRole("user")
			.form("/login", "/login?error=true");
		
	}
	
	public void uninstall() throws Throwable {
		uninstallPageTemplates();
		uninstallMenu();
		uninstallSecurityConfig();
		uninstallListeners();
	}

	private void uninstallPageTemplates() {
	}
	
	private void uninstallSecurityConfig() {
		SecurityRegistry securityRegistry = EntityContextPlugin.getEntity(SecurityRegistry.class);
		securityRegistry.unregisterAuthorization("CONTENT");
	}
	
	public void uninstallMenu() throws Throwable {
		ObjectsManager objectsManager = EntityContextPlugin.getEntity(ObjectsManager.class);
		objectsManager.unregisterDriver(globalDriver);
		objectsManager.unregisterDriver(menubarObjectDriver);
		objectsManager.unregisterDriver(pageObjectDriver);
	}

	private void uninstallListeners() {
		
		EdiacaranListenerManager ediacaranListenerManager = EntityContextPlugin.getEntity(EdiacaranListenerManager.class);
		ediacaranListenerManager.removeListener(EntityContextPlugin.getEntity(ThemeEdiacaranListener.class));
		ediacaranListenerManager.removeListener(EntityContextPlugin.getEntity(LanguageRequestListener.class));
		
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
