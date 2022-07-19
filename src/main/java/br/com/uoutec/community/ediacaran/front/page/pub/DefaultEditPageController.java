package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.CharArrayReader;
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
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;

import br.com.uoutec.community.ediacaran.front.page.BreadcrumbPath;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.page.PageManager.PageMetadata;
import br.com.uoutec.pub.entity.InvalidRequestException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages/editor/default")
@DefaultThrowSafe(rendered=false)
public class DefaultEditPageController {

	@Transient
	@Inject
	public PageManager pageManager;

	@Action("/save")
	@RequestMethod(RequestMethodTypes.POST)
	@View(value="/pages/admin/save-result")
	public void save(
			String path,
			String name,
			@Basic(mappingType=MappingTypes.VALUE)
			Locale locale,
			String title,
			String content,
			Map<String, String> header,
			List<BreadcrumbPath> breadcrumb,
			String template) throws InvalidRequestException{

		try {
			PageMetadata pg = pageManager.unique(path, true, (e)->{
				Locale l = (Locale) e.getExtMetadata("locale");
				boolean result = locale == null? l == null : locale.equals(l);
				result = result && (name == null? true : e.getName().contains(name));
				return result;
			});
			
			if(pg == null) {
				throw new InvalidRequestException("not found");
			}
			
			Page page = new Page();
			page.setBreadcrumb(breadcrumb);
			page.setHeader(header);
			page.setTemplate(template);
			page.setTitle(title);
			page.setContent(content == null? null : new CharArrayReader(content.toCharArray()));
			pageManager.registerPage(path, name, locale, page);
		}
		catch(InvalidRequestException ex) {
			throw ex;
		}
		catch(Throwable ex) {
			throw new InvalidRequestException("interna error: ", ex);
		}		
	}
	
}
