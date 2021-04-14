package br.com.uoutec.community.ediacaran.front.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Actions;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.ResponseErrors;

import br.com.uoutec.community.ediacaran.PluginConfigurationManager;
import br.com.uoutec.community.ediacaran.core.security.GuaranteedAccessTo;
import br.com.uoutec.community.ediacaran.core.security.UserPrivilege;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widgets;
import br.com.uoutec.community.ediacaran.plugins.PluginConfiguration;
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

	@Action(value="/config/plugins")
	@View("/${plugins.ediacaran.front.template}/admin/plugins")
	@Result("vars")
	public Map<String, Object> plugins(){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("plugins", widgets.getWidgets());
		return m;
	}
	
	public List<String> getGroups(){
		return pluginConfigurationManager.getGroups();
	}
	
	public List<PluginConfiguration> getPlugins(String group){
		return pluginConfigurationManager.getPluginsConfiguartion(group);
	}
	
}
