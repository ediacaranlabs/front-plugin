package br.com.uoutec.community.ediacaran.front.page.pub;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.DetachedName;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.ThrowSafe;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.RequestMethod;
import org.brandao.brutos.annotation.web.RequestMethodTypes;
import org.brandao.brutos.validator.ValidatorException;

import br.com.uoutec.community.ediacaran.front.page.PageManager;
import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;
import br.com.uoutec.pub.entity.InvalidRequestException;

@Singleton
@Controller(value="${plugins.ediacaran.front.admin_context}/pages/editor/default")
@DefaultThrowSafe(rendered=false)
public class DefaultEditPageController {

	@Transient
	@Inject
	public PageManager editPage;

	@Action("/save")
	@RequestMethod(RequestMethodTypes.POST)
	@View(value="/admin/pages/save-result")
	@ThrowSafe( rendered=true, target=ValidatorException.class, view="/admin/pages/validation-exception")
	@Result(value="id", mappingType=MappingTypes.VALUE)
	public Map<String,Object> save(@DetachedName PagePubEntity pageEntity) throws InvalidRequestException, ValidatorException{

		try {
			Page page = pageEntity.rebuild(pageEntity.getGid() != null, true, true);

			ObjectMetadata omd = ContextSystemSecurityCheck.doPrivileged(()->{
				if(pageEntity.getId() != null) {
					return editPage.registerPageByName(pageEntity.getPath(), 
							pageEntity.getId(), pageEntity.getLocale(), page);
				}
				else {
					return editPage.registerPageByTitle(pageEntity.getPath(), 
							pageEntity.getTitle(), pageEntity.getLocale(), page);
				}
			});
			
			Map<String,Object> md = new HashMap<String,Object>();
			md.put("path", omd.getPathMetadata().getPath());
			md.put("id", omd.getPathMetadata().getId());
			md.put("locale", omd.getLocale());
			return md;
		}
		catch(InvalidRequestException ex) {
			ex.printStackTrace();
			throw ex;
		}
		catch(ValidatorException ex) {
			throw ex;
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			throw new InvalidRequestException("internal error: ", ex);
		}		
	}
	
}
