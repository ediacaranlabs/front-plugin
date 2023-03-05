package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.CharArrayReader;
import java.util.HashMap;
import java.util.List;
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

import br.com.uoutec.community.ediacaran.front.components.ImageField;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.page.BreadcrumbPath;
import br.com.uoutec.community.ediacaran.front.page.EditPage;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageFileManagerHandler;
import br.com.uoutec.pub.entity.InvalidRequestException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages/editor/default")
@DefaultThrowSafe(rendered=false)
public class DefaultEditPageController {

	@Transient
	@Inject
	public EditPage editPage;

	@Action("/save")
	@RequestMethod(RequestMethodTypes.POST)
	@View(value="/pages/admin/save-result")
	@ThrowSafe( rendered=true, target=ValidatorException.class, view="/pages/admin/validation-exception")
	@Result(value="id", mappingType=MappingTypes.VALUE)
	public Map<String,Object> save(
			Long gid,
			@NotNull
			@Size(max = 600)
			@Pattern(regexp="(\\/|" + PageFileManagerHandler.PATH_FORMAT + ")")
			//@Pattern(regexp=PageFileManagerHandler.PATH_FORMAT)
			String path,
			@Size(max = 255)
			@Pattern(regexp=PageFileManagerHandler.ID_FORMAT)
			String name,
			@Pattern(regexp=PageFileManagerHandler.LOCALE_FORMAT)
			String locale,
			@NotNull
			@Size(max = 255)
			String title,
			@Size(max = 2097152)
			String content,
			Map<String, String> header,
			List<BreadcrumbPath> breadcrumb,
			ImageField thumbnail,
			@Size(max = 255)
			String titleThumbnail,
			@Size(max = 255)
			String shortDescription,
			@NotNull
			@Size(max = 120)
			String template) throws InvalidRequestException, ValidatorException{

		try {
			Page page = new Page();
			page.setBreadcrumb(breadcrumb);
			page.setHeader(header);
			page.setThumbnailDescription(shortDescription);
			page.setThumbnailTitle(titleThumbnail);
			page.setThumbnail(thumbnail == null? null : thumbnail.save(560, 292));
			page.setTemplate(template);
			page.setTitle(title);
			page.setContent(content == null? null : new CharArrayReader(content.toCharArray()));
			
			if(gid == null) {

				if(!editPage.isValidTemplate(template)) {
					throw new InvalidRequestException("invalid template");
				}
				
				ObjectMetadata omd = name == null? 
						editPage.registerPageByTitle(path, title, locale, page) :
						editPage.registerPageByName(path, name, locale, page);

				Map<String,Object> md = new HashMap<String,Object>();
				md.put("path", omd.getPathMetadata().getPath());
				md.put("id", omd.getPathMetadata().getId());
				md.put("locale", omd.getLocale());

				return md;
			}
			else {
				
				Map<String,Object> md = new HashMap<String,Object>();
				md.put("path", path);
				md.put("id", name);
				md.put("locale", locale);
				
				if(gid != md.hashCode()) {
					throw new InvalidRequestException("invalid id");
				}
				
				editPage.registerPageByName(path, name, locale, page);

				return null;
			}
			
		}
		catch(InvalidRequestException ex) {
			ex.printStackTrace();
			throw ex;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			throw new InvalidRequestException("internal error: ", ex);
		}		
	}
	
}
