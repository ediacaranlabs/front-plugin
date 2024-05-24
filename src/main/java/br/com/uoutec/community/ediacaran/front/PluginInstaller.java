package br.com.uoutec.community.ediacaran.front;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import br.com.uoutec.application.SystemProperties;
import br.com.uoutec.application.io.Vfs;
import br.com.uoutec.application.io.VfsException;
import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.community.ediacaran.front.UserEventListenerManager.UserEvent;
import br.com.uoutec.community.ediacaran.front.objects.MenubarObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.objects.PageObjectTemplateType;
import br.com.uoutec.community.ediacaran.front.objects.PagesObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
import br.com.uoutec.community.ediacaran.front.pub.MenuBarManagerException;
import br.com.uoutec.community.ediacaran.front.security.pub.AuthenticationMethodBuilder;
import br.com.uoutec.community.ediacaran.front.security.pub.WebSecurityManagerPlugin;
import br.com.uoutec.community.ediacaran.front.theme.PluginThemesManager;
import br.com.uoutec.community.ediacaran.security.AuthorizationManager;
import br.com.uoutec.community.ediacaran.system.i18n.Plugini18nManager;
import br.com.uoutec.community.ediacaran.system.repository.FileManager;
import br.com.uoutec.community.ediacaran.system.repository.FileObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.JsonFileManagerHandler;
import br.com.uoutec.community.ediacaran.system.repository.ObjectHandlerImp;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManager;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManagerDriverException;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsTemplateManager;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsTemplateManagerDriver;
import br.com.uoutec.ediacaran.core.AbstractPlugin;
import br.com.uoutec.ediacaran.core.EdiacaranListenerManager;
import br.com.uoutec.ediacaran.core.VarParser;
import br.com.uoutec.ediacaran.core.VarParserConfiguration;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;
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
		ContextSystemSecurityCheck.doPrivileged(()->{
			installPageTemplates();
			installMenu();
			installSecurityConfig();
			installListeners();
			installThemes();
			installI18n();
			installVarParser();
			return null;
		});
	}

	private void installVarParser() {
		VarParserConfiguration vpc = 
				(VarParserConfiguration) EntityContextPlugin.getEntity(VarParser.class);
		
		vpc.registerVarResolver("theme", new ThemeVarResolver());
	}
	
	private void installI18n() throws VfsException, IOException, ReflectiveOperationException {
		Plugini18nManager pi18n = EntityContextPlugin.getEntity(Plugini18nManager.class);
		pi18n.registerLanguages();
	}
	
	private void installThemes() throws VfsException, IOException, ReflectiveOperationException {
		PluginThemesManager ptm = EntityContextPlugin.getEntity(PluginThemesManager.class);
		ptm.registerThemes();
	}
	
	private void installPageTemplates() {
	}

	private void installMenu() throws MenuBarManagerException, ObjectsManagerDriverException {

		/* Global Object Driver */
		
		this.globalDriver = 
				new FileObjectsManagerDriver(
						new FileManager(
								Vfs.getPath(SystemProperties.getProperty("app.base") + "/" + OBJECTS_REPOSITORY), 
								new JsonFileManagerHandler()
						), "global"
				); 

		this.globalDriver.setDefaultObjectHandler(new ObjectHandlerImp());
		
		/* Menu Object Driver */
		
		this.menubarObjectDriver = EntityContextPlugin.getEntity(MenubarObjectsManagerDriver.class);
		
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
		//ediacaranListenerManager.addListener(EntityContextPlugin.getEntity(ThemeEdiacaranListener.class));
		ediacaranListenerManager.addListener(EntityContextPlugin.getEntity(LanguageRequestListener.class));
	}
	
	private void installSecurityConfig() throws RegistryException {
		
		AuthorizationManager am = EntityContextPlugin.getEntity(AuthorizationManager.class);
		
		am.registerAuthorization("CONTENT", 				"Content - Manager","Content Manager", null);
		am.registerAuthorization("CONTENT:PAGES",			"Page - Manager",	"Page Manager", null);
		am.registerAuthorization("CONTENT:PAGES:LIST",		"Page - List",		"List Pages", 	null);
		am.registerAuthorization("CONTENT:PAGES:SAVE",		"Page - Save",		"Save Pages", 	null);
		am.registerAuthorization("CONTENT:PAGES:UPDATE",	"Page - Update",	"Update Pages", null);
		am.registerAuthorization("CONTENT:PAGES:DELETE",	"Page - Delete",	"Delete Pages", null);
		
		am.registerAuthorization("CONTENT:MENUBAR",			"Menubar - Manager","Menubar Manager", 	null);
		am.registerAuthorization("CONTENT:MENUBAR:LIST",	"Menubar - List",	"List Menubar", 	null);
		am.registerAuthorization("CONTENT:MENUBAR:SAVE",	"Menubar - Save",	"Save Menubar", 	null);
		am.registerAuthorization("CONTENT:MENUBAR:UPDATE",	"Menubar - Update",	"Update Menubar", 	null);
		am.registerAuthorization("CONTENT:MENUBAR:DELETE",	"Menubar - Delete",	"Delete Menubar", 	null);
		
		WebSecurityManagerPlugin webSecurityManagerPlugin = 
				EntityContextPlugin.getEntity(WebSecurityManagerPlugin.class);
	
		VarParser varParser = EntityContextPlugin.getEntity(VarParser.class);
		
		webSecurityManagerPlugin
			.addConstraint(varParser.getValue("${plugins.ediacaran.front.manager_context}/*"))
				.addRole("manager")
			.addConstraint(varParser.getValue("/templates/default_template${plugins.ediacaran.front.manager_context}/*"))
				.addRole("manager")
			.addConstraint(varParser.getValue("${plugins.ediacaran.front.admin_context}/*"))
				.addRole("manager")
				.addRole("user")
			.addConstraint(varParser.getValue("/templates/default_template${plugins.ediacaran.front.admin_context}/*"))
				.addRole("manager")
				.addRole("user")
			.form()
				.setOption(AuthenticationMethodBuilder.LOGIN_PAGE, "/login")
				.setOption(AuthenticationMethodBuilder.ERROR_PAGE, "/login?error=true");
		
	}
	
	public void uninstall() throws Throwable {
		ContextSystemSecurityCheck.doPrivileged(()->{
			uninstallI18n();
			uninstallThemes();
			uninstallPageTemplates();
			uninstallMenu();
			uninstallSecurityConfig();
			uninstallListeners();
			uninstallVarParser();
			return null;
		});
	}

	private void uninstallVarParser() {
		VarParserConfiguration vpc = 
				(VarParserConfiguration) EntityContextPlugin.getEntity(VarParser.class);
		
		vpc.unregisterVarResolver("theme");
	}
	
	private void uninstallPageTemplates() {
	}
	
	private void uninstallSecurityConfig() {
		AuthorizationManager am = EntityContextPlugin.getEntity(AuthorizationManager.class);
		am.unregisterAuthorization("CONTENT");
	}
	
	public void uninstallMenu() throws Throwable {
		ObjectsManager objectsManager = EntityContextPlugin.getEntity(ObjectsManager.class);
		objectsManager.unregisterDriver(globalDriver);
		objectsManager.unregisterDriver(menubarObjectDriver);
		objectsManager.unregisterDriver(pageObjectDriver);
	}

	private void uninstallListeners() {
		EdiacaranListenerManager ediacaranListenerManager = EntityContextPlugin.getEntity(EdiacaranListenerManager.class);
		//ediacaranListenerManager.removeListener(EntityContextPlugin.getEntity(ThemeEdiacaranListener.class));
		ediacaranListenerManager.removeListener(EntityContextPlugin.getEntity(LanguageRequestListener.class));
		
	}

	private void uninstallThemes() throws VfsException, IOException, ReflectiveOperationException {
		PluginThemesManager ptm = EntityContextPlugin.getEntity(PluginThemesManager.class);
		ptm.unregisterThemes();
	}
	
	private void uninstallI18n() throws VfsException, IOException, ReflectiveOperationException {
		Plugini18nManager pi18n = EntityContextPlugin.getEntity(Plugini18nManager.class);
		pi18n.unregisterLanguages();
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
