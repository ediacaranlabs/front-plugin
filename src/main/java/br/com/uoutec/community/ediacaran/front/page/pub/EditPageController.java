package br.com.uoutec.community.ediacaran.front.page.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.brandao.brutos.web.WebDispatcherType;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.core.security.RequiresPermissions;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.page.EditPage;
import br.com.uoutec.community.ediacaran.front.page.ObjectTemplate;
import br.com.uoutec.community.ediacaran.front.page.Page;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages", defaultActionName="/")
@DefaultThrowSafe(rendered=false)
public class EditPageController {

	@Transient
	@Inject
	private EditPage editpage;
	
	@Action("/")
	@Result("itens")
	@RequiresPermissions("CONTENT:PAGES:LIST")
	public WebResultAction index(WebResultAction webResult){
		webResult = list(null, null, webResult);
		webResult.setView("/pages/admin/index");
		return webResult;
	}
	
	@Action("/list")
	@RequestMethod(RequestMethodTypes.POST)
	@Result("itens")
	@RequiresPermissions("CONTENT:PAGES:LIST")
	public WebResultAction list(
			@Basic(bean="path")
			String path,
			String locale,
			WebResultAction webResult){
		
		webResult.setView("/pages/admin/table");
		
		try {
			List<ObjectMetadata> list     = editpage.list(path, locale);
			Map<Locale, String> langNames = editpage.getSupportedLocales();
			
			webResult.add("itens", list);
			webResult.add("locales", langNames);
		}
		catch(Throwable ex) {
			webResult
			.add("exception", ex);
		}
			
		return webResult;
	}

	@Action("/new")
	@RequiresPermissions("CONTENT:PAGES:CREATE")
	public WebResultAction selectTemplate(WebResultAction webResult){
		
		webResult.setView("/pages/admin/select_template");
		
		try {
			Map<String,ObjectTemplate> templates = editpage.getTemplates();
			webResult.add("templates", templates);
		}
		catch(Throwable ex) {
			webResult
			.add("exception", ex);
		}
		
		return webResult;
	}

	@Action("/delete")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:PAGES:DELETE")
	public WebResultAction delete(
			Long gid,
			String path,
			String name, 
			@Basic(mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", name);
			md.put("locale", locale);
			
			if(gid == md.hashCode()) {
				editpage.unregisterPageByName(path, name, locale);
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
			String name,
			@Basic(mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		webResult.setView("/pages/admin/confirm_delete");
		
		try {
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", name);
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
	
	@Action("/new")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:PAGES:CREATE")
	public WebResultAction create(String templateName, WebResultAction webResult){
		
		try {
			Map<Locale, String> langNames = editpage.getSupportedLocales();
			Map<String,ObjectTemplate> templates = editpage.getTemplates();
			ObjectTemplate template = editpage.getTemplate(templateName);
			
			webResult.setView(template.getFormPath(), true);
			webResult.setDispatcher(WebDispatcherType.FORWARD);
			webResult
				.add("templates", templates)
				.add("locales", langNames)
				.add("template", templateName);

			return webResult;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			WebFlowController
				.redirect()
				.put("exception", ex)
				.to("${plugins.ediacaran.front.admin_context}/pages/list");
			return null;
		}
	}
	
	@Action("/edit")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresPermissions("CONTENT:PAGES:EDIT")
	public WebResultAction edit(
			String path,
			String name,
			@Basic(mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			
			Page page = editpage.getPageByName(path, name, locale);
			
			if(page == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.admin_context}/pages/list");
			}
			
			Map<Locale, String> langNames = editpage.getSupportedLocales();
			Map<String,ObjectTemplate> templates = editpage.getTemplates();
			ObjectTemplate template = editpage.getTemplate(page.getTemplate());
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", name);
			md.put("locale", PluginLanguageUtils.toLocale(locale));
			
			webResult.setView(template.getFormPath(), true);
			webResult.setDispatcher(WebDispatcherType.FORWARD);
			
			webResult
				.add("page", page)
				.add("templates", templates)
				.add("metadata", md)
				.add("locales", langNames)
				.add("template", page.getTemplate());

			return webResult;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			WebFlowController
				.redirect()
				.put("exception", ex)
				.to("${plugins.ediacaran.front.admin_context}/pages/list");
			return null;
		}
	}
	
}
