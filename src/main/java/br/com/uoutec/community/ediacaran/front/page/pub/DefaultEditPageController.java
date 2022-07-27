package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.CharArrayReader;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;

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
	public void save(
			Long gid,
			@NotNull
			@NotEmpty
			@Pattern(regexp=PageManager.PATH_FORMAT)
			String path,
			@Pattern(regexp=PageManager.ID_FORMAT)
			String name,
			@Pattern(regexp=PageManager.LOCALE_FORMAT)
			String locale,
			@NotNull
			@NotEmpty
			String title,
			String content,
			Map<String, String> header,
			List<BreadcrumbPath> breadcrumb,
			@NotNull
			@NotEmpty
			String template) throws InvalidRequestException{

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
			
			pageManager.registerPage(pg.getPath(), pg.getId(), pg.getLocale(), page);
		}
		catch(InvalidRequestException ex) {
			throw ex;
		}
		catch(Throwable ex) {
			throw new InvalidRequestException("interna error: ", ex);
		}		
	}
	
}
