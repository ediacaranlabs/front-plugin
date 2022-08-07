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

import br.com.uoutec.community.ediacaran.core.system.i18n.LanguageRegistry;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.page.PageManager.PageMetadata;
import br.com.uoutec.community.ediacaran.front.page.PageManager.PageMetadataImp;
import br.com.uoutec.community.ediacaran.front.page.PageTemplateManager.PageTemplate;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages", defaultActionName="/")
@DefaultThrowSafe(rendered=false)
public class EditPageController {

	@Transient
	@Inject
	public PageManager pageManager;

	@Transient
	@Inject
	private LanguageRegistry languageRegistry;
	
	@Action("/")
	@Result("itens")
	//@View(value="/pages/admin/index")
	public WebResultAction index(WebResultAction webResult){
		webResult = list(null, null, webResult);
		webResult.setView("/pages/admin/index");
		return webResult;
	}
	
	@Action("/list")
	@RequestMethod(RequestMethodTypes.POST)
	@Result("itens")
	//@View(value="/pages/admin/table")
	public WebResultAction list(
			@Basic(bean="path")
			String fullPath,
			String locale,
			WebResultAction webResult){
		
		String path = null;
		String name = null;
		
		if(fullPath != null) {
			
			fullPath = fullPath.replaceAll("/+", "/");
			int lastIndex = fullPath.lastIndexOf("/");
			
			if(lastIndex == -1) {
				path = ".*".concat(Arrays.stream(fullPath.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*"))).concat(".*");
				name = path;
			}
			else
			if(lastIndex == 0) {
				path = Arrays.stream(fullPath.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*")).concat(".*");
				name = null;
			}
			else{
				path = fullPath.substring(0, lastIndex);
				name = fullPath.substring(lastIndex + 1, fullPath.length());
				
				path = ".*".concat(Arrays.stream(path.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*"))).concat(".*");
				name = ".*".concat(Arrays.stream(name.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*"))).concat(".*");
			}
			
		}
		
		String pathMath = path;
		String nameMath = name;
		
		Locale loc = PluginLanguageUtils.toLocale(locale);
		List<PageMetadata> list = 
			pageManager.list(null, true, (e)->{
				Locale l = (Locale) e.getExtMetadata("locale");
				boolean result = loc == null? true : loc.equals(l);
				result = result && (
						(pathMath == null? true : e.getPath().matches(pathMath)) &&
						(nameMath == null? true : e.getName().matches(nameMath))
				);
				return result;
			});
		
		Map<Locale, String> langNames = languageRegistry.getSupportedLocalesName();
		Map<String,PageTemplate> editors = pageManager.getTemplatesIdMap();
		webResult
			.setView("/pages/admin/table")
			.add("itens", list)
			.add("locales", langNames)
			.add("editors", editors);
		
		return webResult;
	}

	@Action("/new")
	public WebResultAction selectTemplate(WebResultAction webResult){
		
		List<PageTemplate> templates = pageManager.getTemplates();
		webResult
			.setView("/pages/admin/select_template")
			.add("templates", templates);
		
		return webResult;
	}

	@Action("/delete")
	@RequestMethod(RequestMethodTypes.POST)
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
				pageManager.unregisterPage(path, name, loc);
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
	public WebResultAction create(String templateName, WebResultAction webResult){
		
		try {
			Map<Locale, String> langNames = languageRegistry.getSupportedLocalesName();
			List<PageTemplate> templates = pageManager.getTemplates();
			PageTemplate template = templateName == null? templates.get(0) : pageManager.getTemplate(templateName);
			
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
	public WebResultAction edit(
			String path, 
			String name,
			@Basic(mappingType=MappingTypes.VALUE)
			String locale,
			WebResultAction webResult){
		
		try {
			Locale loc = PluginLanguageUtils.toLocale(locale);
			PageMetadata pg = new PageMetadataImp(path, name, loc);

			Page page = pageManager.getPage(pg);
			
			if(page == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.admin_context}/pages/list");
			}
			
			Map<Locale, String> langNames = languageRegistry.getSupportedLocalesName();
			List<PageTemplate> templates = pageManager.getTemplates();
			PageTemplate template = page == null? templates.get(0) : pageManager.getTemplate(page.getTemplate());
			
			Map<String,Object> md = new HashMap<>();
			md.put("path", pg.getPath());
			md.put("id", pg.getId());
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
