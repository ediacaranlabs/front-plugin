package br.com.uoutec.community.ediacaran.front.page.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Transient;

import br.com.uoutec.community.ediacaran.front.page.EditMenubar;
import br.com.uoutec.community.ediacaran.front.page.PageFileManagerHandler;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
import br.com.uoutec.i18n.ValidationException;
import br.com.uoutec.pub.entity.AbstractPubEntity;

public class MenubarPubEntity 
	extends AbstractPubEntity<MenuBar>{

	private static final long serialVersionUID = -796010157890313726L;

	private Long gid;
	
	@NotNull
	@Size(max = 600)
	@Pattern(regexp="(\\/|" + PageFileManagerHandler.PATH_FORMAT + ")")
	private String path;
	
	@NotNull
	@Size(max = 255)
	@Pattern(regexp=PageFileManagerHandler.ID_FORMAT)
	private String id;

	@NotNull
	@Size(min = 5)
	private String name;
	
	@Pattern(regexp=PageFileManagerHandler.LOCALE_FORMAT)
	private String locale;
	
	@Basic(mappingType=MappingTypes.OBJECT)
	private List<MenuPubEntity> menus;
 
	@Transient
	private EditMenubar editMenubar;
	
	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public EditMenubar getEditMenubar() {
		return editMenubar;
	}

	public void setEditMenubar(EditMenubar editMenubar) {
		this.editMenubar = editMenubar;
	}

	public List<MenuPubEntity> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuPubEntity> menus) {
		this.menus = menus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	protected boolean isEqualId(MenuBar instance) throws Throwable {
		return false;
	}

	@Override
	protected boolean hasId(MenuBar instance) throws Throwable {
		return gid != null;
	}

	@Override
	protected MenuBar reloadEntity() throws Throwable {
		MenuBar mb = editMenubar.getMenubarById(path, id, locale);
		
		if(mb == null) {
			throw new NullPointerException();
		}
		
		return new MenuBar(id);
	}

	@Override
	protected void throwReloadEntityFail() throws Throwable {
		throw new ValidationException();
	}

	@Override
	protected MenuBar createNewInstance() throws Throwable {
		return new MenuBar(id);
	}

	@Override
	protected void copyTo(MenuBar o, boolean reload, boolean override, boolean validate) throws Throwable {
		
		o.setName(name);

		if(menus != null) {
			for(MenuPubEntity e: menus) {
				e.setParentMenuBar(o);
				Menu m = e.rebuild(false, true, true);
				o.addMenu(m);
			}
		}
		
	}

	protected void validate(Class<?> ... groups) throws ValidationException{
		super.validate(groups);
		
		if(gid != null) {
			Map<String,Object> md = new HashMap<String,Object>();
			md.put("path", path);
			md.put("id", name);
			md.put("locale", locale);

			if(gid != md.hashCode()) {
				throw new ValidationException("invalid gid");
			}
			
		}
		
	}
	
}
