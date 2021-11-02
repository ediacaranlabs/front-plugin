package br.com.uoutec.community.ediacaran.front.pub;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.uoutec.community.ediacaran.VarParser;
import br.com.uoutec.community.ediacaran.core.security.AuthorizationManager;
import br.com.uoutec.community.ediacaran.core.security.Subject;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginMessageBundleUtils;
import br.com.uoutec.community.ediacaran.core.system.util.IDGenerator;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

public class Menu implements Serializable {

	private static final long serialVersionUID = -110898947175676961L;

	private AuthorizationManager authorizationManager;
	
	private PropertyChangeSupport propertyChangeSupport;

	private String id;
	
	private String name;
	
	private String icon;
	
	private String resource;
	
	private String body;
	
	private String resourceBundle;
	
	private String badgeStyle;
	
	private String template;
	
	private List<Menu> itens;
	
	private Map<String, Menu> map;
	
	private final Menu parent;

	private final MenuBar parentMenuBar;
	
	private String role;
	
	private String permission;
	
	private int order;

	public Menu(){
		this(null, null, null, null, null, null, new ArrayList<Menu>(), null, null, null, null, null, 0);
	}

	public Menu(String id, Menu parent, MenuBar parentMenuBar){
		this(id, null, null, null, null, null, new ArrayList<Menu>(), null, null, null, parent, parentMenuBar, 0);
	}
	
	public Menu(String id, String name, String icon, String resource,
			String resourceBundle, String template, String badge, String badgeStyle, String body, Menu parent, MenuBar parentMenuBar, int order) {
		this(id, name, icon, resource, resourceBundle, template, new ArrayList<Menu>(),
				badge, badgeStyle, body, parent, parentMenuBar, order);
	}
	
	public Menu(String id, String name, String icon, String resource,
			String resourceBundle, String template, List<Menu> itens,
			String badge, String badgeStyle, String body, Menu parent, MenuBar parentMenuBar, int order) {
		super();
		this.id = id == null? IDGenerator.getUniqueOrderID('M', this.hashCode()) : id;
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.name = name;
		this.icon = icon;
		this.resource = resource;
		this.resourceBundle = resourceBundle;
		this.template = template;
		this.itens = itens;
		this.order = order;
		this.badgeStyle = badgeStyle;
		this.parent = parent;
		this.parentMenuBar = parentMenuBar;
		this.map = new HashMap<String, Menu>();
		this.authorizationManager = EntityContextPlugin.getEntity(AuthorizationManager.class);
		/*
		if(itens != null) {
			for(Menu i: itens) {
				addItem(i);
			}
		}
		*/
	}

	private String getPath() {
		
		StringBuilder sb = new StringBuilder();
		
		if(parent != null) {
			sb.append(parent.getPath()).append(".");
		}
		else {
			sb.append(parentMenuBar.getId()).append(".");
		}
		
		sb.append(id);
		
		return sb.toString();
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Menu getParent() {
		return parent;
	}

	public String getFullName(){
		
		if(resourceBundle != null) {
			Locale locale = PluginMessageBundleUtils.getLocale();
			return PluginMessageBundleUtils.getMessageResourceString(this.resourceBundle, this.template, locale);
		}
		
		return name;
	}
	
	public String getBody() {
		return body;
	}

	public Menu setBody(String body) {
		
		String oldBody = this.body;
		this.body = body;
		propertyChangeSupport.firePropertyChange("body", oldBody, body);
		
		return this;
	}

	public String getName() {
		return name;
	}

	public Menu setName(String name) {
		
		String oldName = this.name;
		this.name = name;
		propertyChangeSupport.firePropertyChange("name", oldName, name);
		
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public Menu setIcon(String icon) {
		
		String oldIcon = this.icon;
		this.icon = icon;
		propertyChangeSupport.firePropertyChange("icon", oldIcon, icon);
		
		return this;
	}

	public String getRawResource() {
		return resource;
	}
	
	public String getResource() {
		VarParser varParser = EntityContextPlugin.getEntity(VarParser.class);
		return varParser.getValue(resource);
	}

	public Menu setResource(String resource) {
		
		String oldResource = this.resource;
		this.resource = resource;
		propertyChangeSupport.firePropertyChange("resource", oldResource, resource);
		
		return this;
	}

	public String getResourceBundle() {
		return resourceBundle;
	}

	public Menu setResourceBundle(String resourceBundle) {
		
		String oldResourceBundle = this.resourceBundle;
		this.resourceBundle = resourceBundle;
		propertyChangeSupport.firePropertyChange("resourceBundle", oldResourceBundle, resourceBundle);
		
		return this;
	}

	public String getTemplate() {
		return template;
	}

	public Menu setTemplate(String template) {

		String oldTemplate = this.template;
		this.template = template;
		propertyChangeSupport.firePropertyChange("template", oldTemplate, template);
		
		return this;
	}

	public int getOrder() {
		return order;
	}

	public Menu setOrder(int order) {
		
		int oldOrder = this.order;
		this.order = order;
		propertyChangeSupport.firePropertyChange("order", oldOrder, order);
		
		return this;
	}

	public String getBadgeStyle() {
		return badgeStyle;
	}

	public Menu setBadgeStyle(String badgeStyle) {
		
		String oldBadgeStyle = this.badgeStyle; 
		this.badgeStyle = badgeStyle;
		propertyChangeSupport.firePropertyChange("badgeStyle", oldBadgeStyle, badgeStyle);
		
		return this;
	}

/*
	public List<Menu> getItens() {
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + getPath() + ".list.*"));
		}
		
		return Collections.unmodifiableList(itens);
	}
*/
	public List<Menu> getItens() {
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + getPath() + ".list.*"));
		}

		List<Menu> result =  new ArrayList<Menu>();
		
		Subject subject = authorizationManager.getSubject();
		
		for(Menu m: itens) {
			
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
	}
	
	public String getBadge() {
		MenuBadgeValue value = EntityContextPlugin.getEntity(MenuBadgeValue.class);
		return value == null? null : value.getValue(id);
	}

	public Menu setBadge(String badge) {

		MenuBadgeValue value = EntityContextPlugin.getEntity(MenuBadgeValue.class);
		String oldBadge = value == null? null : value.getValue(id);
		
		if(value != null) {
			value.setValue(id, badge);
		}
		
		propertyChangeSupport.firePropertyChange("badge", oldBadge, badge);
		
		return this;
	}

	public Menu addItem(String id){
		
		synchronized (this) {
			
			SecurityManager sm = System.getSecurityManager();
			
			if(sm != null) {
				sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + getPath() + ".register"));
			}
			
			if(map.containsKey(id)) {
				throw new IllegalStateException("menu exist: " + id);
			}
			
			Menu item = new Menu(id, this, null);
			
			this.itens.add(item);
			this.map.put(item.getId(), item);
			
			Collections.sort(this.itens, new Comparator<Menu>(){

				public int compare(Menu o1, Menu o2) {
					return o2.getOrder() - o1.getOrder();
				}
				
			});
			
			propertyChangeSupport.fireIndexedPropertyChange("item", itens.size() - 1, null, item);
			
			return item;
		}
	}

	public Menu getItem(String name){
		
		SecurityManager sm = System.getSecurityManager();
		
		if(sm != null) {
			sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + getPath() + ".list"));
		}
		
		return map.get(name);
	}
	
	public Menu removeItem(String name){
		synchronized (this) {
			
			SecurityManager sm = System.getSecurityManager();
			
			if(sm != null) {
				sm.checkPermission(new RuntimePermission(MenuBarManager.basePermission + "." + getPath() + ".remove"));
			}
			
			Menu m = map.get(name);
			
			if(m == null) 
				return this;
			
			itens.remove(m);
			map.remove(m.getId());
			
			propertyChangeSupport.fireIndexedPropertyChange("item", itens.size(), m, null);
			
			return this;
		}
	}

    public void addPropertyChangeListener(PropertyChangeListener listener) {

        propertyChangeSupport.addPropertyChangeListener(listener);

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {

    	propertyChangeSupport.removePropertyChangeListener(listener);

    }
    
	public String getPermission() {
		return permission;
	}

	public Menu setPermission(String permission) {
		
		String oldValue = this.permission;
		this.permission = permission;
		propertyChangeSupport.firePropertyChange("permission", oldValue, permission);
		
		return this;
	}

	public String getRole() {
		return role;
	}

	public Menu setRole(String role) {
		
		String oldValue = this.role;
		this.role = role;
		propertyChangeSupport.firePropertyChange("role", oldValue, role);
		
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
