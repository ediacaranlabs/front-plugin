package br.com.uoutec.community.ediacaran.front.page.pub;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.web.WebDispatcherType;
import org.brandao.brutos.web.WebFlowController;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.page.PageManager.PageMetadata;
import br.com.uoutec.community.ediacaran.front.page.PageTemplateManager.PageTemplate;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages", defaultActionName="/")
@DefaultThrowSafe(rendered=false)
public class EditPageController {

	@Transient
	@Inject
	public PageManager pageManager;

	@Action("/")
	@Result("itens")
	@View(value="/pages/admin/index")
	public List<PageMetadata> index(){
		return list(null, null);
	}
	
	@Action("/list")
	@RequestMethod(RequestMethodTypes.POST)
	@Result("itens")
	@View(value="/pages/admin/table")
	public List<PageMetadata> list(
			String name,
			@Basic(mappingType=MappingTypes.VALUE)
			String localeSTR){
		
		Locale locale = localeSTR == null || localeSTR.trim().length() == 0? null : Locale.forLanguageTag(localeSTR);
		return pageManager.list(null, true, (e)->{
			Locale l = (Locale) e.getExtMetadata("locale");
			boolean result = locale == null? true : locale.equals(l);
			result = result && (name == null? true : e.getName().contains(name));
			return result;
		});		
	}

	@Action("/edit")
	@RequestMethod(RequestMethodTypes.POST)
	public WebResultAction edit(
			String path, 
			String name,
			@Basic(mappingType=MappingTypes.VALUE)
			Locale locale,
			WebResultAction webResult){
		
		try {
			PageMetadata pg = pageManager.unique(path, true, (e)->{
				Locale l = (Locale) e.getExtMetadata("locale");
				boolean result = locale == null? l == null : locale.equals(l);
				result = result && (name == null? true : e.getName().contains(name));
				return result;
			});
			
			if(pg == null) {
				WebFlowController
				.redirect()
				.to("${plugins.ediacaran.front.admin_context}/pages");
				return null;
			}
			
			Page page = pageManager.getPage(pg);
			List<PageTemplate> templates = pageManager.getTemplates();
			PageTemplate template = page == null? templates.get(0) : pageManager.getTemplate(page.getTemplate());
			
			webResult.setView(template.getFormPath(), true);
			webResult.setDispatcher(WebDispatcherType.FORWARD);
			webResult.add("page", page);
			webResult.add("templates", templates);

			return webResult;
		}
		catch(Throwable ex) {
			WebFlowController
				.redirect()
				.put("exception", ex)
				.to("${plugins.ediacaran.front.admin_context}/pages");
			return null;
		}
	}
	
}
