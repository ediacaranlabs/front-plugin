package br.com.uoutec.community.ediacaran.front.page.pub;

import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	private Map<String, MenuPubEntity> menus;
 
	private Integer order;
	
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
		
		if(menus != null) {
			
			for(Entry<String, MenuPubEntity> e: menus.entrySet()) {
				MenuPubEntity m = e.getValue();
				m.setParent(o);
				Menu x = m.rebuild(false, true, true);
				o.addItem(x);
			}
			
		}
		
	}
	
}
