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
			String name,
			String locale,
			WebResultAction webResult){
		
		Locale loc = PluginLanguageUtils.toLocale(locale);
		List<PageMetadata> list = 
			pageManager.list(null, true, (e)->{
				Locale l = (Locale) e.getExtMetadata("locale");
				boolean result = loc == null? true : loc.equals(l);
				result = result && (name == null? true : e.getName().contains(name));
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
	public WebResultAction create(String templateName, WebResultAction webResult){
		
		try {
			Map<Locale, String> langNames = languageRegistry.getSupportedLocalesName();
			List<PageTemplate> templates = pageManager.getTemplates();
			PageTemplate template = templateName == null? templates.get(0) : pageManager.getTemplate(templateName);
			
			webResult.setView(template.getFormPath(), true);
			webResult.setDispatcher(WebDispatcherType.FORWARD);
			webResult
				.add("templates", templates)
				.add("locales", langNames);

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
				.add("locales", langNames);

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
