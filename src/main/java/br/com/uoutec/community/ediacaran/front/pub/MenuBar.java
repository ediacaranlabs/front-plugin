package br.com.uoutec.community.ediacaran.front.pub;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.core.security.AuthorizationManager;
import br.com.uoutec.community.ediacaran.core.security.Subject;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

public class MenuBar {

	private final AuthorizationManager authorizationManager;
	
	private final String id;
	
	private final PropertyChangeSupport propertyChangeSupport;
	
	private final List<Menu> list;
	
	private final Map<String, Menu> map;
	
	public MenuBar(String id){
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.list = new ArrayList<Menu>();
		this.map = new HashMap<String, Menu>();
		this.id = id;
		this.authorizationManager = EntityContextPlugin.getEntity(AuthorizationManager.class);
	}
	
	public String getId() {
		return id;
	}

	public List<Menu> getItens(){
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + this.id + ".list"));
		}

		List<Menu> result =  new ArrayList<Menu>();
		
		Subject subject = authorizationManager.getSubject();
		
		for(Menu m: list) {
			
			String role = m.getRole();

			if(role == null) {
				result.add(m);
				continue;
			}
			
			if(subject != null && subject.hasRole(role)) {
			
				String permission = m.getPermission();

				if(permission == null) {
					result.add(m);
					continue;
				}
				
				if(subject.isPermitted(permission)) {
					result.add(m);
				}
			}
			
		}
		
		return Collections.unmodifiableList(result);
		
		//return this.list;
	}
	
	public Menu addMenu(String id){
		
		synchronized (this) {
			
			SecurityManager sm = System.getSecurityManager();
			
			if(sm != null) {
				sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + this.id + ".register"));
			}
			
			if(map.containsKey(id)) {
				throw new IllegalStateException("menu exist: " + id);
			}

			Menu menu = new Menu(id, null, this);
			
			this.list.add(menu);
			this.map.put(menu.getId(), menu);
			
			Collections.sort(this.list, new Comparator<Menu>(){

				public int compare(Menu o1, Menu o2) {
					return o2.getOrder() - o1.getOrder();
				}
				
			});
			
			propertyChangeSupport.fireIndexedPropertyChange("menu", list.size() - 1, null, menu);
			
			return menu;
		}
	}

	public Menu getMenu(String id){
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + this.id + "." + id + ".list"));
		}
		
		return map.get(id);
	}
	
	public void removeMenu(String id){
		synchronized (this) {
			
			SecurityManager sm = System.getSecurityManager();
			
			if(sm != null) {
				sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + this.id + "." + id + ".remove"));
			}
			
			Menu m = this.map.get(id);
			
			if(m == null) 
				return;
			
			list.remove(m);
			map.remove(id);
			
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
