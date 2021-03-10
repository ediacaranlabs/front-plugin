package br.com.uoutec.community.ediacaran.front.pub;

import java.util.HashMap;
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

import br.com.uoutec.community.ediacaran.core.security.GuaranteedAccessTo;
import br.com.uoutec.community.ediacaran.core.security.UserPrivilege;
import br.com.uoutec.community.ediacaran.front.pub.widget.Widgets;
import br.com.uoutec.community.ediacaran.system.pub.MenuBar;
import br.com.uoutec.community.ediacaran.system.pub.MenuBarManager;
import br.com.uoutec.community.ediacaran.system.pub.MenuBarManagerException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}", defaultActionName="/")
@View(value="/${plugins.ediacaran.front.template}/admin/index")
@Actions({
	@Action(value="/", view=@View(value="/${plugins.ediacaran.front.template}/admin/index")),
	@Action(value="/user-menu", view=@View(value="/${plugins.ediacaran.front.template}/admin/includes/user_menu"))
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

}
