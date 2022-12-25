package br.com.uoutec.community.ediacaran.front.page.pub;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
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
import org.brandao.brutos.web.WebDispatcherType;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.core.security.RequiresPermissions;
import br.com.uoutec.community.ediacaran.core.system.i18n.LanguageRegistry;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.SearchType;
import br.com.uoutec.community.ediacaran.front.objects.PagesObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.objects.PathMetadata;
import br.com.uoutec.community.ediacaran.front.page.ObjectTemplate;
import br.com.uoutec.community.ediacaran.front.page.ObjectsTemplateManager;
import br.com.uoutec.community.ediacaran.front.page.Page;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages", defaultActionName="/")
@DefaultThrowSafe(rendered=false)
public class EditPageController {

	@Transient
	@Inject
	public ObjectsTemplateManager objectsManager;

	@Transient
	@Inject
	private LanguageRegistry languageRegistry;
	
	@Action("/")
	@Result("itens")
	//@View(value="/pages/admin/index")
	@RequiresPermissions("CONTENT:PAGES:LIST")
	public WebResultAction index(WebResultAction webResult){
		webResult = list(null, null, webResult);
		webResult.setView("/pages/admin/index");
		return webResult;
	}
	
	@Action("/list")
	@RequestMethod(RequestMethodTypes.POST)
	@Result("itens")
	//@View(value="/pages/admin/table")
	@RequiresPermissions("CONTENT:PAGES:LIST")
	public WebResultAction list(
			@Basic(bean="path")
			String fullPath,
			String locale,
			WebResultAction webResult){
		
		Locale loc  = PluginLanguageUtils.toLocale(locale);
		String path = null;
		String name = null;

		fullPath = fullPath == null? null : fullPath.replaceAll("/+", "/");
		int lastIndex = fullPath == null? -1 : fullPath.lastIndexOf("/");
		
		if(lastIndex > 0 ) {
			path = PagesObjectsManagerDriver.DRIVER_NAME + fullPath.substring(0, lastIndex);
			name = fullPath.substring(lastIndex + 1, fullPath.length());
			
			name = ".*".concat(Arrays.stream(name.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*"))).concat(".*");
		}
		else {
			path = PagesObjectsManagerDriver.DRIVER_NAME + (fullPath == null? "" : fullPath);
		}
		
		List<ObjectMetadata> list = objectsManager.listMetadata(path, name, loc, true, SearchType.REGEX);
		
		Map<Locale, String> langNames      = languageRegistry.getSupportedLocalesName();
		Map<String,ObjectTemplate> editors = objectsManager.getTemplatesIdMap(PagesObjectsManagerDriver.DRIVER_NAME);
		
		webResult
			.setView("/pages/admin/table")
			.add("itens", list)
			.add("locales", langNames)
			.add("editors", editors);
		
		return webResult;
	}

	@Action("/new")
	@RequiresPermissions("CONTENT:PAGES:CREATE")
	public WebResultAction selectTemplate(WebResultAction webResult){
		
		List<ObjectTemplate> templates = objectsManager.getTemplates(PagesObjectsManagerDriver.DRIVER_NAME);
		webResult
			.setView("/pages/admin/select_template")
			.add("templates", templates);
		
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
			Locale loc = PluginLanguageUtils.toLocale(locale);
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", name);
			md.put("locale", loc);
			
			if(gid == md.hashCode()) {
				objectsManager.unregisterObject(path + "/" + name, loc);
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
			Locale loc = PluginLanguageUtils.toLocale(locale);
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", path);
			md.put("id", name);
			md.put("locale", loc);
			
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
			Map<Locale, String> langNames = languageRegistry.getSupportedLocalesName();
			List<ObjectTemplate> templates = objectsManager.getTemplates(PagesObjectsManagerDriver.DRIVER_NAME);
			ObjectTemplate template = templateName == null? templates.get(0) : objectsManager.getTemplateById(PagesObjectsManagerDriver.DRIVER_NAME, templateName);
			
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
			Locale loc = PluginLanguageUtils.toLocale(locale);
			ObjectMetadata pg = new ObjectMetadata(new PathMetadata(PagesObjectsManagerDriver.DRIVER_NAME, path, name), loc); 

			Page page = (Page)objectsManager.getObject(pg);
			
			if(page == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.admin_context}/pages/list");
			}
			
			Map<Locale, String> langNames = languageRegistry.getSupportedLocalesName();
			List<ObjectTemplate> templates = objectsManager.getTemplates(PagesObjectsManagerDriver.DRIVER_NAME);
			ObjectTemplate template = page == null? templates.get(0) : objectsManager.getTemplateById(PagesObjectsManagerDriver.DRIVER_NAME, page.getTemplate());
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", pg.getPathMetadata().getPath());
			md.put("id", pg.getPathMetadata().getId());
			md.put("locale", pg.getLocale());
			md.put("template", page.getTemplate());
			
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
