package br.com.uoutec.community.ediacaran.front.pub;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.inject.Singleton;

@Singleton
@Deprecated
public class MenuBarManagerImp implements MenuBarManager{

	private ConcurrentMap<String, MenuBar> map;
	
	public MenuBarManagerImp(){
		this.map = new ConcurrentHashMap<String, MenuBar>();
	}
	
	@Override
	public void registerMenuBar(MenuBar menuBar) throws MenuBarManagerException {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + "." + menuBar.getId() + ".register"));
		}
		
		if(this.map.containsKey(menuBar.getId())){
			throw new MenuBarManagerException("menu bar has already been registered: " + menuBar.getId());
		}
		
		this.map.put(menuBar.getId(), menuBar);

	}

	@Override
	public MenuBar getMenuBar(String name) throws MenuBarManagerException {

		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + "." + name + ".list"));
		}
		
		MenuBar menubar = this.map.get(name);
		
		if(menubar == null){
			throw new MenuBarManagerException("menubar not found: " + name);
		}
		
		return menubar;
	}

	@Override
	public void removeMenuBar(String name) throws MenuBarManagerException {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(basePermission + "." + name + ".remove"));
		}
		
		map.remove(name);
	}

}
