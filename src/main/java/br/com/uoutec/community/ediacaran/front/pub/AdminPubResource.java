package br.com.uoutec.community.ediacaran.front.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import br.com.uoutec.community.ediacaran.core.security.GuaranteedAccessTo;
import br.com.uoutec.community.ediacaran.core.security.UserPrivilege;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widgets;
import br.com.uoutec.community.ediacaran.plugins.MutablePluginConfiguration;
import br.com.uoutec.community.ediacaran.plugins.PluginConfiguration;
import br.com.uoutec.community.ediacaran.plugins.PluginStatus;
import br.com.uoutec.community.ediacaran.system.pub.MenuBar;
import br.com.uoutec.community.ediacaran.system.pub.MenuBarManager;
import br.com.uoutec.community.ediacaran.system.pub.MenuBarManagerException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}", defaultActionName="/")
@View(value="/${plugins.ediacaran.front.template}/admin/index")
@Actions({
	@Action(value="/", view=@View(value="/${plugins.ediacaran.front.template}/admin/index")),
	@Action(value="/user-menu", view=@View(value="/${plugins.ediacaran.front.template}/admin/user_menu")),
	@Action(value="/plugins", view=@View(value="/${plugins.ediacaran.front.template}/admin/plugins"))
})
@GuaranteedAccessTo(UserPrivilege.class)
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

	@Action(value="/plugins/{code}")
	@View("/${plugins.ediacaran.front.template}/admin/plugin-detail")
	@Result(value="vars", mappingType=MappingTypes.VALUE)
	public Map<String,Object> plugins(@Basic(bean="code") String code){
		
		PluginConfiguration pc = pluginConfigurationManager.getPluginConfiguartion(code);
		
		if(pc == null)
			throw new NullPointerException("PluginConfiguration");
		
		
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("config", pc);
		vars.put("status", pluginConfigurationManager.getStatus(pc));
		
		return vars;
	}

	@Action(value="/plugins/{code}")
	@RequestMethod("POST")
	@View("/${plugins.ediacaran.front.template}/admin/update-plugin-detail")
	public void updatePlugin(@Basic(bean="code") String code, @Basic(bean="config") Map<String,List<String>> values){
		
		MutablePluginConfiguration mpc = (MutablePluginConfiguration)pluginConfigurationManager.getPluginConfiguartion(code);
		
		if(values != null && mpc != null) {
			for(Entry<String, List<String>> e: values.entrySet()) {
				mpc.setStrings(e.getKey(), e.getValue());
			}
			
			pluginConfigurationManager.savePluginMetadata(mpc);
		}
		
	}

	@Action(value="/plugins/{code}/status")
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
	
	public List<String> getGroups(){
		return pluginConfigurationManager.getGroups();
	}
	
	public List<PluginConfiguration> getPlugins(String group){
		return pluginConfigurationManager.getPluginsConfiguartion(group);
	}
	
}
