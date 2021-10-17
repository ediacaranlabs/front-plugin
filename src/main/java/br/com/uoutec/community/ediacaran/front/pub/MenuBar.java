package br.com.uoutec.community.ediacaran.front.pub;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBar {

	private final String id;
	
	private final PropertyChangeSupport propertyChangeSupport;
	
	private final List<Menu> list;
	
	private final Map<String, Menu> map;
	
	public MenuBar(String id){
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.list = new ArrayList<Menu>();
		this.map = new HashMap<String, Menu>();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public List<Menu> getItens(){
		return this.list;
	}
	
	public void addMenu(Menu menu){
		
		synchronized (this) {
			
			SecurityManager sm = System.getSecurityManager();
			
			if(sm != null) {
				sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + id + ".register"));
			}
			
			if(map.containsKey(menu.getId())) {
				throw new IllegalStateException("menu exist: " + menu.getName());
			}
			
			this.list.add(menu);
			this.map.put(menu.getId(), menu);
			menu.setParentMenuBar(this);
			
			Collections.sort(this.list, new Comparator<Menu>(){

				public int compare(Menu o1, Menu o2) {
					return o2.getOrder() - o1.getOrder();
				}
				
			});
			
			propertyChangeSupport.fireIndexedPropertyChange("menu", list.size() - 1, null, menu);
			
		}
	}

	public Menu getMenu(String name){
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + id + "." + name + ".list"));
		}
		
		return map.get(name);
	}
	
	public void removeMenu(Menu menu){
		synchronized (this) {
			
			SecurityManager sm = System.getSecurityManager();
			
			if(sm != null) {
				sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + id + "." + menu.getId() + ".remove"));
			}
			
			Menu m = this.map.get(menu.getId());
			
			if(m == null) 
				return;
			
			list.remove(m);
			map.remove(m.getId());
			
			propertyChangeSupport.fireIndexedPropertyChange("menu", list.size(), m, null);
			
		}
	}
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {

        propertyChangeSupport.addPropertyChangeListener(listener);

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {

    	propertyChangeSupport.removePropertyChangeListener(listener);

    }
	
}
