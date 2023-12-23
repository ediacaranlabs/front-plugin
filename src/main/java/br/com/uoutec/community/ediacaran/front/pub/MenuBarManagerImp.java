package br.com.uoutec.community.ediacaran.front.pub;

@Deprecated
public class MenuBarManagerImp implements MenuBarManager{

	@Override
	public void registerMenuBar(MenuBar menuBar) throws MenuBarManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuBar getMenuBar(String name) throws MenuBarManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeMenuBar(String name) throws MenuBarManagerException {
		// TODO Auto-generated method stub
		
	}
	
/*
	private ConcurrentMap<String, MenuBar> map;
	
	public MenuBarManagerImp(){
		this.map = new ConcurrentHashMap<String, MenuBar>();
	}
	
	@Override
	public void registerMenuBar(MenuBar menuBar) throws MenuBarManagerException {
		
		SecurityUtil.checkPermission(new RuntimePermission(basePermission + "." + menuBar.getId() + ".register"));
		
		if(this.map.containsKey(menuBar.getId())){
			throw new MenuBarManagerException("menu bar has already been registered: " + menuBar.getId());
		}
		
		this.map.put(menuBar.getId(), menuBar);

	}

	@Override
	public MenuBar getMenuBar(String name) throws MenuBarManagerException {

		SecurityUtil.checkPermission(new RuntimePermission(basePermission + "." + name + ".list"));
		
		MenuBar menubar = this.map.get(name);
		
		if(menubar == null){
			throw new MenuBarManagerException("menubar not found: " + name);
		}
		
		return menubar;
	}

	@Override
	public void removeMenuBar(String name) throws MenuBarManagerException {
		
		SecurityUtil.checkPermission(new RuntimePermission(basePermission + "." + name + ".remove"));
		
		map.remove(name);
	}
*/
}
