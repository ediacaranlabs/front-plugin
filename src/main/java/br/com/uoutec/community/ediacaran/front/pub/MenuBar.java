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

	public static final String basePermission = "app.menu";

	private final AuthorizationManager authorizationManager;
	
	private final String id;
	
	private String name;
	
	private final PropertyChangeSupport propertyChangeSupport;
	
	private final List<Menu> list;
	
	private final Map<String, Menu> map;
	
	public MenuBar(){
		this(null);
	}

	public MenuBar(String id){
		this(id, null);
	}
	
	public MenuBar(String id, List<Menu> itens){
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.list = itens == null? new ArrayList<Menu>() : itens;
		this.map = new HashMap<String, Menu>();
		this.id = id;
		this.authorizationManager = EntityContextPlugin.getEntity(AuthorizationManager.class);
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBar.basePermission + "." + this.id + ".name"));
		}

		String oldName = this.name;
		this.name = name;
		propertyChangeSupport.firePropertyChange("resourceBundle", oldName, name);
	}

	public List<Menu> getItens(){
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBar.basePermission + "." + this.id + ".list"));
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
		Menu menu = new Menu(id, this);
		addMenu(menu);
		return menu;
	}

	public synchronized void addMenu(Menu ... menu) {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBar.basePermission + "." + this.id + ".register"));
		}
		
		for(Menu m: menu) {
			
			if(map.containsKey(m.getId())) {
				throw new IllegalStateException("menu exist: " + id);
			}
	
			this.list.add(m);
			this.map.put(m.getId(), m);
		
			propertyChangeSupport.fireIndexedPropertyChange("menu", list.size() - 1, null, m);
		}
		
		Collections.sort(this.list, new Comparator<Menu>(){

			public int compare(Menu o1, Menu o2) {
				return o2.getOrder() - o1.getOrder();
			}
			
		});
		
		
	}
	
	public Menu getMenu(String id){
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBar.basePermission + "." + this.id + "." + id + ".list"));
		}
		
		return map.get(id);
	}
	
	public void removeMenu(String id){
		synchronized (this) {
			
			SecurityManager sm = System.getSecurityManager();
			
			if(sm != null) {
				sm.checkPermission(new RuntimePermission(MenuBar.basePermission + "." + this.id + "." + id + ".remove"));
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
