package br.com.uoutec.community.ediacaran.front.page;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.ActionStrategy;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.ScopeType;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.web.WebActionStrategyType;
import org.brandao.brutos.web.HttpStatus;
import org.brandao.brutos.web.WebDispatcherType;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;
import br.com.uoutec.ediacaran.core.plugins.PublicType;

@Singleton
@ActionStrategy(WebActionStrategyType.DETACHED)
public class PageController implements PublicType {

	@Inject
	@Transient
	private PageManager pageManager;

	@Action("{uri:(/+[a-z0-9][a-z0-9]+(-[a-z0-9]+)*)+}")
	public WebResultAction execute(
			@Basic(bean="uri") String uri, 
			@Basic(bean="locale", scope=ScopeType.REQUEST, mappingType=MappingTypes.VALUE) Locale locale, 
			WebResultAction result) {
		
		if(uri == null) {
			result.setResponseStatus(HttpStatus.NOT_FOUND);
			result.setReason("page not found!");
			return result;
		}
		
		Page page;
		
		try {
			page = pageManager.getPage(uri, locale);
			
			if(page == null && locale != null) {
				page = pageManager.getPage(uri, (String)null);
			}
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			result.setResponseStatus(HttpStatus.NOT_FOUND);
			result.setReason("page can't be reached!");
			return result;
		}

		try {
			if(page != null) {
				ObjectTemplate pg = pageManager.getTemplate(page.getTemplate(), PageObjectTemplateType.VIEW); 
				result.setView(pg.getTemplate(), true);
				result.setDispatcher(WebDispatcherType.FORWARD);
				result.add("page", page);
			}
			else {
				result.setResponseStatus(HttpStatus.NOT_FOUND);
				result.setReason(uri + " not found!");
			}
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			result.setResponseStatus(HttpStatus.NOT_FOUND);
			result.setReason("page can't be reached!");
			return result;
		}
		
		return result;
		
	}
}
