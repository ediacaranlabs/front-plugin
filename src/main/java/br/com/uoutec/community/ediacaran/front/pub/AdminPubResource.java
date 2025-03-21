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

import br.com.uoutec.community.ediacaran.front.pub.widget.Widgets;
import br.com.uoutec.ediacaran.core.PluginConfigurationManager;
import br.com.uoutec.ediacaran.core.plugins.PluginConfiguration;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}", defaultActionName="/")
@View(value="${plugins.ediacaran.front.template}/admin/index")
@Actions({
	@Action(value="/", view=@View(value="${plugins.ediacaran.front.template}/admin/index")),
	@Action(value="/user-menu", view=@View(value="${plugins.ediacaran.front.template}/admin/user_menu"))
})
@ResponseErrors(rendered=false, name="exception")
public class AdminPubResource {

	@Inject
	@Transient
	private Widgets widgets;

	@Inject
	@Transient
	private PluginConfigurationManager pluginConfigurationManager;

	public AdminPubResource(){
	}

	@Action(value="/dashboard")
	@View("${plugins.ediacaran.front.template}/admin/dashboard")
	@Result("vars")
	public Map<String, Object> getDashboard(){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("widgets", widgets.getWidgets());
		return m;
	}
	
	public List<String> getGroups(){
		return pluginConfigurationManager.getGroups();
	}
	
	public List<PluginConfiguration> getPlugins(String group){
		return pluginConfigurationManager.getPluginsConfiguartion(group);
	}
	
}
