package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.CharArrayReader;
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
	public PageMetadata save(
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

			if(template == null || pageManager.getTemplate(template) == null ) {
				throw new InvalidRequestException("invalid template");
			}
			
			Page page = new Page();
			page.setBreadcrumb(breadcrumb);
			page.setHeader(header);
			page.setTemplate(template);
			page.setTitle(title);
			page.setContent(content == null? null : new CharArrayReader(content.toCharArray()));
			
			if(gid == null) {
				
				if(name == null) {
					name = title;
				}
				
				return pageManager.registerPageIfNotExist(path, name, loc, page);
			}
			else {
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
