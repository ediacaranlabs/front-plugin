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
import org.brandao.brutos.annotation.DetachedName;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.web.MediaTypes;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.web.WebDispatcherType;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.page.PageObjectTemplateType;
import br.com.uoutec.community.ediacaran.security.BasicRoles;
import br.com.uoutec.community.ediacaran.security.RequiresPermissions;
import br.com.uoutec.community.ediacaran.security.RequiresRole;
import br.com.uoutec.community.ediacaran.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;
import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;
import br.com.uoutec.pub.entity.InvalidRequestException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages", defaultActionName="/")
@DefaultThrowSafe(rendered=false)
public class EditPageController {

	@Transient
	@Inject
	private PageManager editpage;
	
	@Action("/")
	@RequiresRole(BasicRoles.USER)
	@RequiresPermissions("CONTENT:PAGES:LIST")
	public WebResultAction index(WebResultAction webResult){
		webResult.setView("/admin/pages/index");
		webResult.add("locales", editpage.getSupportedLocales());
		return webResult;
	}
	
	@Action("/list")
	@RequestMethod(RequestMethodTypes.POST)
	@ResponseType(MediaTypes.APPLICATION_JSON)
	@RequiresRole(BasicRoles.USER)
	@RequiresPermissions("CONTENT:PAGES:LIST")
	public Serializable list(
			@DetachedName PageSearchPubEntity request){
		
		PageSearch pageSearch;
		
		try {
			pageSearch = request.rebuild(false, true, true);
			
			List<ObjectMetadata> list =
					ContextSystemSecurityCheck.doPrivileged(()->editpage.list(pageSearch.getPath(), pageSearch.getLocale()));
			
			Map<Locale, String> localeMap = editpage.getSupportedLocales();

			int[] index = {1};
			List<PageSearchResponse> result = list.stream().map((e)->new PageSearchResponse(index[0]++, e, localeMap)).collect(Collectors.toList());

			return new SearchResult<PageSearchResponse>(-1, 1, false, result);
		}
		catch(Throwable ex) {
			throw new InvalidRequestException("internal error: ", ex);
		}
	}

	@Action("/new")
	@RequiresRole(BasicRoles.USER)
	@RequiresPermissions("CONTENT:PAGES:CREATE")
	public WebResultAction selectTemplate(WebResultAction webResult){
		
		webResult.setView("/admin/pages/select_template");
		
		try {
			Map<String,ObjectTemplate> templates = editpage.getTemplates(PageObjectTemplateType.FORM);
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
	@RequiresRole(BasicRoles.USER)
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
				ContextSystemSecurityCheck.doPrivileged(()->{
					editpage.unregisterPage(path, id, locale);
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
	@RequiresRole(BasicRoles.USER)
	@RequiresPermissions("CONTENT:PAGES:DELETE")
	public WebResultAction confirmDelete(
			@Basic(bean="path")
			String path, 
			@Basic(bean="id")
			String id,
			@Basic(bean="locale", mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		webResult.setView("/admin/pages/confirm_delete");
		
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
	
	@Action("/new")
	@RequestMethod(RequestMethodTypes.POST)
	@RequiresRole(BasicRoles.USER)
	@RequiresPermissions("CONTENT:PAGES:CREATE")
	public WebResultAction create(
			@Basic(bean="templateName")
			String templateName, 
			WebResultAction webResult){
		
		try {
			Map<Locale, String> langNames = editpage.getSupportedLocales();
			Map<String,ObjectTemplate> templates = editpage.getTemplates(PageObjectTemplateType.FORM);
			ObjectTemplate template = editpage.getTemplate(templateName, PageObjectTemplateType.FORM);
			
			webResult.setView(template.getTemplate(), true);
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
	@RequiresRole(BasicRoles.USER)
	@RequiresPermissions("CONTENT:PAGES:EDIT")
	public WebResultAction edit(
			@Basic(bean="path")
			String path,
			@Basic(bean="id")
			String id,
			@Basic(bean="locale", mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			
			Page page =
					ContextSystemSecurityCheck.doPrivileged(()->editpage.getPageById(path, id, locale));
			
			if(page == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/list");
			}
			
			Map<Locale, String> langNames = editpage.getSupportedLocales();
			Map<String,ObjectTemplate> templates = editpage.getTemplates(PageObjectTemplateType.FORM);
			ObjectTemplate template = editpage.getTemplate(page.getTemplate(), PageObjectTemplateType.FORM);
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", id);
			md.put("locale", PluginLanguageUtils.toLocale(locale));
			
			webResult.setView(template.getTemplate(), true);
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
				.to("${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/pages/list");
			return null;
		}
	}
	
}
