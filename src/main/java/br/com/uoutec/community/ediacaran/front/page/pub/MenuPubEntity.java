package br.com.uoutec.community.ediacaran.front.page.pub;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Transient;

import br.com.uoutec.community.ediacaran.front.page.PageFileManagerHandler;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
import br.com.uoutec.i18n.ValidationException;
import br.com.uoutec.pub.entity.AbstractPubEntity;

public class MenuPubEntity 
	extends AbstractPubEntity<Menu>{

	private static final long serialVersionUID = -796010157890313726L;

	@NotNull
	@Size(max = 255)
	@Pattern(regexp=PageFileManagerHandler.ID_FORMAT)
	private String id;

	@NotNull
	private String name;
	
	private String icon;
	
	@Basic(mappingType=MappingTypes.OBJECT)
	private List<MenuPubEntity> menus;
 
	private String resource;
	
	private Integer order;
	
	private String role;
	
	private String permission;
	
	@Transient
	private Menu parent;
	
	@Transient
	private MenuBar parentMenuBar;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public List<MenuPubEntity> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuPubEntity> menus) {
		this.menus = menus;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public MenuBar getParentMenuBar() {
		return parentMenuBar;
	}

	public void setParentMenuBar(MenuBar parentMenuBar) {
		this.parentMenuBar = parentMenuBar;
	}

	@Override
	protected boolean isEqualId(Menu instance) throws Throwable {
		return false;
	}

	@Override
	protected boolean hasId(Menu instance) throws Throwable {
		return id != null;
	}

	@Override
	protected Menu reloadEntity() throws Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void throwReloadEntityFail() throws Throwable {
		throw new ValidationException();
	}

	@Override
	protected Menu createNewInstance() throws Throwable {
		return new Menu(id, parent, parentMenuBar);
	}

	@Override
	protected void copyTo(Menu o, boolean reload, boolean override, boolean validate) throws Throwable {
		
		o.setId(id);
		o.setName(name);
		o.setIcon(icon);
		o.setOrder(order == null? 0 : order);
		o.setPersistent(true);
		o.setResource(resource);
		o.setRole(role);
		o.setPermission(permission);
		
		if(menus != null) {
			
			for(MenuPubEntity e: menus) {
				e.setParent(o);
				Menu x = e.rebuild(false, true, true);
				o.addItem(x);
			}
			
		}
		
	}
	
}
