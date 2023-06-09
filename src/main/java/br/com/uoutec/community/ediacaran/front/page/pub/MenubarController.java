package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.DetachedName;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.ThrowSafe;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.validator.ValidatorException;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.core.security.RequiresPermissions;
import br.com.uoutec.community.ediacaran.core.security.Role;
import br.com.uoutec.community.ediacaran.core.security.SecurityRegistry;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.page.EditMenubar;
import br.com.uoutec.community.ediacaran.front.page.EditPage;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;
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
	private EditPage editPage;

	@Transient
	@Inject
	private SecurityRegistry securityRegistry;
	
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
			String locale,
			WebResultAction webResult){
		
		webResult.setView("/pages/admin/table");
		
		try {
			List<ObjectMetadata> list     = editMenubar.list(path, locale);
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
			String path,
			String id,
			@Basic(mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			
			MenuBar menuBar = editMenubar.getMenubarById(path, id, locale);
			
			if(menuBar == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.admin_context}/menubar/list");
			}
			
			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			Set<Role> roles = securityRegistry.getAll();
			
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
			MenuBar menuBar = menubarPubEntity.rebuild(menubarPubEntity.getGid() != null, true, true);
			
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
	@RequiresPermissions("CONTENT:MENUBAR:NEW-MENU")
	public WebResultAction newMenu(
			WebResultAction webResult){
		
		try {
			
			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			Set<Role> roles = securityRegistry.getAll();
			
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
			Long gid,
			String path,
			String id, 
			@Basic(mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", id);
			md.put("locale", locale);
			
			if(gid == md.hashCode()) {
				editMenubar.unregisterMenubarById(path, id, locale);
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
			String path, 
			String id,
			@Basic(mappingType=MappingTypes.VALUE)
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
	public Serializable searchResource(
			String value){
		
		List<ObjectMetadata> list = editPage.list(value, (Locale)null);
		
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
