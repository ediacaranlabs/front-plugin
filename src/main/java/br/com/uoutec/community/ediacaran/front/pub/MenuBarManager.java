package br.com.uoutec.community.ediacaran.front.pub;

import br.com.uoutec.ediacaran.core.plugins.PublicBean;

@Deprecated
public interface MenuBarManager extends PublicBean{

	public static final String basePermission = "app.menu";
	
	void registerMenuBar(MenuBar menuBar) throws MenuBarManagerException;
	
	MenuBar getMenuBar(String name) throws MenuBarManagerException;
	
	void removeMenuBar(String name) throws MenuBarManagerException;
	
}
