package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.core.security.RequiresPermissions;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.page.EditMenubar;
import br.com.uoutec.community.ediacaran.front.page.EditPage;
import br.com.uoutec.community.ediacaran.front.pub.Menu;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;

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
			String name,
			@Basic(mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			
			MenuBar menuBar = editMenubar.getMenubarByName(path, name, locale);
			
			if(menuBar == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.admin_context}/menubar/list");
			}
			
			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", name);
			md.put("locale", PluginLanguageUtils.toLocale(locale));
			
			webResult
				.add("menubar", menuBar)
				.add("metadata", md)
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
	
	@Action("/new")
	@RequestMethod(RequestMethodTypes.GET)
	@RequiresPermissions("CONTENT:MENUBAR:NEW")
	public WebResultAction newMenu(
			WebResultAction webResult){
		
		try {
			
			Map<Locale, String> langNames = editMenubar.getSupportedLocales();
			
			Map<String,Object> md = new HashMap<>();
			
			webResult
				.add("param1_", new Menu())
				.add("metadata", md)
				.add("locales", langNames)
				.add("countMenuID", System.currentTimeMillis())
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
