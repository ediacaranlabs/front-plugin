package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.AcceptRequestType;
import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.DetachedName;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.ThrowSafe;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.MediaTypes;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.validator.ValidatorException;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.front.page.EditMenubar;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
import br.com.uoutec.community.ediacaran.security.AuthorizationManager;
import br.com.uoutec.community.ediacaran.security.RequiresPermissions;
import br.com.uoutec.community.ediacaran.security.Role;
import br.com.uoutec.community.ediacaran.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;
import br.com.uoutec.pub.entity.InvalidRequestException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/menubar", defaultActionName="/")
@DefaultThrowSafe(rendered=false)
public class MenubarController {

	@Transient
	@Inject
	private EditMenubar editMenubar;

	@Transient
	@Inject
	private PageManager editPage;

	@Transient
	@Inject
	private AuthorizationManager authorizationManager;
	
	@Action("/")
	@Result("itens")
	@RequiresPermissions("CONTENT:MENUBAR:LIST")
	public WebResultAction index(WebResultAction webResult){
		webResult = list(null, null, webResult);
		webResult.setView("/admin/menubar/index");
		return webResult;
	}
	
	@Action("/list")
	@RequestMethod(RequestMethodTypes.POST)
	@Result("itens")
	@RequiresPermissions("CONTENT:MENUBAR:LIST")
	public WebResultAction list(
			@Basic(bean="path")
			String path,
			@Basic(bean="locale")
			String locale,
			WebResultAction webResult){
		
		webResult.setView("/pages/admin/table");
		
		try {
			List<ObjectMetadata> list =
					AccessController.doPrivileged((PrivilegedAction<List<ObjectMetadata>>)()->editMenubar.list(path, locale));					

			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			
			webResult.add("itens", list);
			webResult.add("locales", langNames);
		}
		catch(Throwable ex) {
			webResult
			.add("exception", ex);
		}
			
		return webResult;
	}

	@Action("/edit")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:MENUBAR:EDIT")
	public WebResultAction edit(
			@Basic(bean="path")
			String path,
			@Basic(bean="id")
			String id,
			@Basic(bean="locale", mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			
			MenuBar menuBar =
					AccessController.doPrivileged((PrivilegedAction<MenuBar>)()->editMenubar.getMenubarById(path, id, locale));					
			
			if(menuBar == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.admin_context}/menubar/list");
			}
			
			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			List<Role> roles = authorizationManager.getAllRoles();
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", id);
			md.put("locale", PluginLanguageUtils.toLocale(locale));
			
			webResult
				.add("menubar", menuBar)
				.add("metadata", md)
				.add("locales", langNames)
				.add("roles", roles)
				.setView("/admin/menubar/menubar");

			return webResult;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			WebFlowController
				.redirect()
				.put("exception", ex)
				.to("${plugins.ediacaran.front.admin_context}/menubar/list");
			return null;
		}
	}

	@Action("/save")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:MENUBAR:SAVE")
	@View(value="/admin/menubar/save-result")
	@ThrowSafe(rendered=true, target=ValidatorException.class, view="/admin/menubar/validation-exception")
	@Result(value="id", mappingType=MappingTypes.VALUE)
	public Map<String,Object> save(
			@Basic(mappingType=MappingTypes.OBJECT)
			@DetachedName
			MenubarPubEntity menubarPubEntity) throws InvalidRequestException{
		
		try {
			
			menubarPubEntity.setEditMenubar(editMenubar);
			MenuBar menuBar =
					AccessController.doPrivileged((PrivilegedAction<MenuBar>)()->menubarPubEntity.rebuild(menubarPubEntity.getGid() != null, true, true));					
			
			ObjectMetadata omd = editMenubar
				.registerMenubarById(
						menubarPubEntity.getPath(), 
						menubarPubEntity.getId(), 
						menubarPubEntity.getLocale(), 
						menuBar
				);
			
			Map<String,Object> md = new HashMap<String,Object>();
			md.put("path", omd.getPathMetadata().getPath());
			md.put("id", omd.getPathMetadata().getId());
			md.put("locale", omd.getLocale());
			return md;
			
		}
		catch(InvalidRequestException ex) {
			ex.printStackTrace();
			throw ex;
		}
		catch(ValidatorException ex) {
			throw ex;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			throw new InvalidRequestException("internal error: ", ex);
		}
		
	}
	
	@Action("/new-menu")
	@RequestMethod(RequestMethodTypes.GET)
	@RequiresPermissions("CONTENT:MENUBAR:NEW_MENU")
	public WebResultAction newMenu(
			WebResultAction webResult){
		
		try {
			
			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			List<Role> roles = authorizationManager.getAllRoles();
			
			Map<String,Object> md = new HashMap<>();
			
			webResult
				.add("param1_", new Menu())
				.add("metadata", md)
				.add("locales", langNames)
				.add("countMenuID", System.currentTimeMillis())
				.add("roles", roles)
				.setView("/admin/menubar/menu");

			return webResult;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			WebFlowController
				.redirect()
				.put("exception", ex)
				.to("${plugins.ediacaran.front.admin_context}/menubar/list");
			return null;
		}
	}

	@Action("/new")
	@RequestMethod(RequestMethodTypes.GET)
	@RequiresPermissions("CONTENT:MENUBAR:NEW")
	public WebResultAction newMenubar(
			WebResultAction webResult){
		
		try {
			
			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			
			webResult
				.add("locales", langNames)
				.setView("/admin/menubar/menubar");

			return webResult;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			WebFlowController
				.redirect()
				.put("exception", ex)
				.to("${plugins.ediacaran.front.admin_context}/menubar/list");
			return null;
		}
	}
	
	@Action("/delete")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:PAGES:DELETE")
	public WebResultAction delete(
			@Basic(bean="gid")
			Long gid,
			@Basic(bean="path")
			String path,
			@Basic(bean="id")
			String id, 
			@Basic(bean="locale", mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", id);
			md.put("locale", locale);
			
			if(gid == md.hashCode()) {
				AccessController.doPrivileged((PrivilegedAction<MenuBar>)()->{
					editMenubar.unregisterMenubarById(path, id, locale);
					return null;
				});					
			}
		}
		catch(Throwable ex) {
			webResult
			.add("exception", ex);
		}
		
		return index(webResult);
	}
	
	@Action("/confirm-delete")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:PAGES:DELETE")
	public WebResultAction confirmDelete(
			@Basic(bean="path")
			String path, 
			@Basic(bean="id")
			String id,
			@Basic(bean="locale", mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		webResult.setView("/admin/menubar/confirm_delete");
		
		try {
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", id);
			md.put("locale", locale);
			
			webResult
				.add("metadata", md);

		}
		catch(Throwable ex) {
			webResult
			.add("exception", ex);
		}
		
		return webResult;
	}
	
	@Action("/search-resource")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:MENUBAR:EDIT")
	@SuppressWarnings("serial")
	@AcceptRequestType(MediaTypes.APPLICATION_JSON)
	@ResponseType(MediaTypes.APPLICATION_JSON)
	public Serializable searchResource(
			@Basic(bean="value")
			String value){
		
		List<ObjectMetadata> list =
				AccessController.doPrivileged((PrivilegedAction<List<ObjectMetadata>>)()->editPage.list(value, (Locale)null));					
		
		if(list == null) {
			return null;
		}
		
		
		return (Serializable)list.stream()
				.map(e-> new HashMap<String,String>(){{
					put("label", e.getPathMetadata().getFullId());
					put("value", e.getPathMetadata().getFullId());
				}})
				.collect(Collectors.toList());
		
	}
	
}
