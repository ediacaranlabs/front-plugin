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
import org.brandao.brutos.annotation.MappingTypes;
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
import br.com.uoutec.community.ediacaran.front.page.PageExistsException;
import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.community.ediacaran.front.page.PageManager.PageMetadata;
import br.com.uoutec.community.ediacaran.front.page.PageNotFoundException;
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
	@Result(value="id", mappingType=MappingTypes.VALUE)
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
			Locale loc = PluginLanguageUtils.toLocale(locale);

			Page page = new Page();
			page.setBreadcrumb(breadcrumb);
			page.setHeader(header);
			page.setTemplate(template);
			page.setTitle(title);
			page.setContent(content == null? null : new CharArrayReader(content.toCharArray()));
			
			if(gid == null) {

				if(template == null || pageManager.getTemplate(template) == null ) {
					throw new InvalidRequestException("invalid template");
				}
				
				if(name == null) {
					name = title;
				}
				
				PageMetadata pg = pageManager.registerPageIfNotExist(path, name, loc, page);

				Map<String,Object> md = new HashMap<String,Object>();
				md.put("path", pg.getPath());
				md.put("id", pg.getId());
				md.put("locale", pg.getLocale());
				md.put("template", page.getTemplate());

				return md;
			}
			else {
				
				Map<String,Object> md = new HashMap<String,Object>();
				md.put("path", path);
				md.put("id", name);
				md.put("locale", loc);
				md.put("template", template);
				
				if(gid != md.hashCode()) {
					throw new InvalidRequestException("invalid id");
				}
				
				pageManager.registerPageIfExist(path, name, loc, page);
				return null;
			}
			
		}
		catch(InvalidRequestException ex) {
			throw ex;
		}
		catch(PageExistsException ex) {
			throw new InvalidRequestException("page has been registeded");
		}		
		catch(PageNotFoundException ex) {
			throw new InvalidRequestException("page not found");
		}		
		catch(Throwable ex) {
			throw new InvalidRequestException("internal error: ", ex);
		}		
	}
	
}