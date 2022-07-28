package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.CharArrayReader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.ThrowSafe;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.validator.ValidatorException;

import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.page.BreadcrumbPath;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.page.PageManager.PageMetadata;
import br.com.uoutec.community.ediacaran.front.page.PageManager.PageMetadataImp;
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
	@ThrowSafe( rendered=true, target=ValidatorException.class, view="/pages/admin/validation-exception")
	@Result("id")
	public Map<String,Object> save(
			Long gid,
			@NotNull
			@Size(max = 120)
			@Pattern(regexp="(\\/|" + PageManager.PATH_FORMAT + ")")
			String path,
			@Size(max = 120)
			@Pattern(regexp=PageManager.ID_FORMAT)
			String name,
			@Pattern(regexp=PageManager.LOCALE_FORMAT)
			String locale,
			@NotNull
			@Size(max = 255)
			String title,
			@Size(max = 6000)
			String content,
			Map<String, String> header,
			List<BreadcrumbPath> breadcrumb,
			@NotNull
			@Size(max = 120)
			String template) throws InvalidRequestException, ValidatorException{

		try {
			PageMetadata pg;
			Page page;
			Locale loc = PluginLanguageUtils.toLocale(locale);
			
			if(gid != null) {
				
				pg = new PageMetadataImp(path, name, loc);
				
				if(gid != pg.hashCode()) {
					throw new InvalidRequestException("invalid id");
				}

				page = pageManager.getPage(pg);
				
				if(page == null) {
					throw new InvalidRequestException("invalid id");
				}
				
			}
			else {
				
				if(name == null) {
					name = title;
				}
				
				pg = new PageMetadataImp(path, name, loc);
				
				page = pageManager.getPage(pg);
				
				if(page != null) {
					throw new InvalidRequestException("invalid id");
				}
				
				page = new Page();
				
			}
			
			if(template == null || pageManager.getTemplate(template) == null ) {
				throw new InvalidRequestException("invalid template");
			}
			
			
			page.setBreadcrumb(breadcrumb);
			page.setHeader(header);
			page.setTemplate(template);
			page.setTitle(title);
			page.setContent(content == null? null : new CharArrayReader(content.toCharArray()));
			
			pg = pageManager.registerPage(pg.getPath(), pg.getId(), pg.getLocale(), page);
			Map<String,Object> id = new HashMap<String, Object>();
			id.put("path", pg.getPath());
			id.put("name", pg.getId());
			id.put("locale", pg.getLocale());
			id.put("gid", pg.hashCode());
			return id;
		}
		catch(InvalidRequestException ex) {
			throw ex;
		}
		catch(Throwable ex) {
			throw new InvalidRequestException("internal error: ", ex);
		}		
	}
	
}
