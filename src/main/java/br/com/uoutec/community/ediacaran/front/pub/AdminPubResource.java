package br.com.uoutec.community.ediacaran.front.pub;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Actions;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.ResponseErrors;

import br.com.uoutec.community.ediacaran.PluginConfigurationManager;
import br.com.uoutec.community.ediacaran.SecurityPermissionUpdater;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widgets;
import br.com.uoutec.community.ediacaran.plugins.MutablePluginConfiguration;
import br.com.uoutec.community.ediacaran.plugins.PluginConfiguration;
import br.com.uoutec.community.ediacaran.plugins.PluginStatus;
import br.com.uoutec.community.ediacaran.security.SecurityPermissionStatus;
import br.com.uoutec.pub.entity.InvalidRequestException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}", defaultActionName="/")
@View(value="/${plugins.ediacaran.front.template}/admin/index")
@Actions({
	@Action(value="/", view=@View(value="/${plugins.ediacaran.front.template}/admin/index")),
	@Action(value="/user-menu", view=@View(value="/${plugins.ediacaran.front.template}/admin/user_menu")),
	@Action(value="/plugins", view=@View(value="/${plugins.ediacaran.front.template}/admin/plugins"))
})
@ResponseErrors(rendered=false, name="exception")
public class AdminPubResource {

	private static final String ADMIN_MENU_BAR 		= "adminMenuBar";
	
	private static final String ADMIN_TOP_MENU_BAR	= "adminTopMenuBar";
	
	@Inject
	@Transient
	private MenuBarManager menuBarManager;
	
	@Inject
	@Transient
	private Widgets widgets;

	@Inject
	@Transient
	private PluginConfigurationManager pluginConfigurationManager;

	@Inject
	@Transient
	private SecurityPermissionUpdater securityPermissionManager;
	
	public AdminPubResource(){
	}

	public MenuBar getMenuBar() throws MenuBarManagerException{
		return menuBarManager.getMenuBar(ADMIN_MENU_BAR);
	}
	
	public MenuBar getTopMenuBar() throws MenuBarManagerException{
		return menuBarManager.getMenuBar(ADMIN_TOP_MENU_BAR);
	}
	
	@Action(value="/dashboard")
	@View("/${plugins.ediacaran.front.template}/admin/dashboard")
	@Result("vars")
	public Map<String, Object> getDashboard(){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("widgets", widgets.getWidgets());
		return m;
	}

	@Action(value="/plugins/{code:[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*}")
	@View("/${plugins.ediacaran.front.template}/admin/plugin-detail")
	@Result(value="vars", mappingType=MappingTypes.VALUE)
	public Map<String,Object> plugins(@Basic(bean="code") String code){
		
		PluginConfiguration pc = pluginConfigurationManager.getPluginConfiguartion(code);
		
		if(pc == null)
			throw new NullPointerException("PluginConfiguration");
		
		
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("config", pc);
		vars.put("status", pluginConfigurationManager.getStatus(pc));
		vars.put("security", securityPermissionManager.getPermissions(pc.getMetadata().getCode()));
		return vars;
	}

	@Action(value="/plugins/{code:[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*}")
	@RequestMethod("POST")
	@View("/${plugins.ediacaran.front.template}/admin/update-plugin-detail")
	public void updatePlugin(
			@Basic(bean="code")
			String code, 
			@Basic(bean="config")
			Map<String,List<String>> values, 
			@Basic(bean="security", mappingType=MappingTypes.OBJECT)
			List<SecurityPermissionStatusPubEntity> security) throws Throwable{
		
		MutablePluginConfiguration mpc = (MutablePluginConfiguration)pluginConfigurationManager.getPluginConfiguartion(code);
		
		if(values != null && mpc != null) {
			for(Entry<String, List<String>> e: values.entrySet()) {
				mpc.setStrings(e.getKey(), e.getValue());
			}
			
			pluginConfigurationManager.savePluginMetadata(mpc);
		}

		if(security != null) {
			Set<SecurityPermissionStatus> status = new HashSet<SecurityPermissionStatus>();
			for(SecurityPermissionStatusPubEntity e: security) {
				status.add(e.rebuild(null,false, true, true, true));
			}
			securityPermissionManager.updatePermissions(mpc.getMetadata().getCode(), status.toArray(new SecurityPermissionStatus[0]));
		}
		
	}

	@Action(value="/plugins/{code:[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*}/status")
	@RequestMethod("POST")
	@View("/${plugins.ediacaran.front.template}/admin/update-status-plugin-detail")
	@Result(value="vars", mappingType=MappingTypes.VALUE)
	public Map<String,Object> updatePlugin(@Basic(bean="code") String code, @Basic(bean="status") Boolean enable){
		
		Map<String,Object> vars = new HashMap<String,Object>();
		
		MutablePluginConfiguration mpc = (MutablePluginConfiguration)pluginConfigurationManager.getPluginConfiguartion(code);
		
		if(mpc != null && enable != null) {
			boolean success = pluginConfigurationManager.setEnable(mpc, Boolean.TRUE.equals(enable));
			
			PluginStatus status = pluginConfigurationManager.getStatus(mpc);
			Throwable error = pluginConfigurationManager.getError(mpc);
			
			vars.put("status", PluginStatus.RUNNING == status);
			vars.put("error", success? null : error);
		}
		
		return vars;
		//WebFlowController.redirect().to("/plugins/ediacaran/front/adm/plugins/" + code);
	}

	@Action(value="/plugins/install-file")
	@RequestMethod("POST")
	@View("/${plugins.ediacaran.front.template}/admin/update-install-file")
	public void installPlugin(
			@Basic(bean="file")
			File pluginPackage) throws InvalidRequestException{

		if(pluginPackage == null) {
			throw new InvalidRequestException("plugin package not found");
		}
		
		try {
			File newFile = 
				new File(
					pluginPackage.getParentFile(), 
					pluginPackage.getName().split("\\.")[0] + ".jar");
			
			if(pluginPackage.renameTo(newFile)) {
				pluginPackage = newFile;
			}
			
			pluginConfigurationManager.install(pluginPackage.toURI().toURL());
		}
		catch(Throwable e) {
			throw new InvalidRequestException("failed to install plugin", e);
		}
		
	}

	@Action(value="/plugins/install-url")
	@RequestMethod("POST")
	@View("/${plugins.ediacaran.front.template}/admin/update-install-file")
	public void installPlugin(
			@Basic(bean="url")
			String urlPluginPackage) throws InvalidRequestException{

		if(urlPluginPackage == null) {
			throw new InvalidRequestException("plugin package not found");
		}

		URL url;
		
		try {
			url = new URL(urlPluginPackage);
		}
		catch(Throwable e) {
			throw new InvalidRequestException("failed to install plugin", e);
		}

		if(!("https".equals(url.getProtocol()) || "http".equals(url.getProtocol()))){
			throw new InvalidRequestException("invalid protocol: " + url.getProtocol());
		}
		
		try {
			pluginConfigurationManager.install(url);
		}
		catch(Throwable e) {
			throw new InvalidRequestException("failed to install plugin", e);
		}
		
	}

	@Action(value="/plugins/{code:[a-zA-Z0-9]+(\\-[a-zA-Z0-9]+)*}/uninstall")
	@RequestMethod("POST")
	@View("/${plugins.ediacaran.front.template}/admin/uninstall-plugin")
	public void uninstallPlugin(
			@Basic(bean="code") String code, @Basic(bean="uninstall_code") String uninstallCode) throws InvalidRequestException{

		if(code == null || !code.equals(uninstallCode) ) {
			throw new InvalidRequestException("plugin not found");
		}

		if(!code.equals(uninstallCode) ) {
			throw new InvalidRequestException("invalid uninstall code: " + code);
		}
		
		try {
			pluginConfigurationManager.uninstall(code);
		}
		catch(Throwable e) {
			throw new InvalidRequestException("failed to uninstall plugin", e);
		}
		
	}
	
	public List<String> getGroups(){
		return pluginConfigurationManager.getGroups();
	}
	
	public List<PluginConfiguration> getPlugins(String group){
		return pluginConfigurationManager.getPluginsConfiguartion(group);
	}
	
}
