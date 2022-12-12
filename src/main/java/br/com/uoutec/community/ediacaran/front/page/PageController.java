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

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager;
import br.com.uoutec.community.ediacaran.plugins.PublicType;

@Singleton
@ActionStrategy(WebActionStrategyType.DETACHED)
public class PageController implements PublicType {

	@Inject
	@Transient
	private ObjectsManager objectsManager;
	
	@Inject
	@Transient
	private ObjectsTemplateManager objectTemplateManager;

	@Action("{uri:(/[a-z][a-z0-9]+(-[a-z0-9]+)*)+}")
	public WebResultAction execute(
			@Basic(bean="uri") String uri, 
			@Basic(bean="locale", scope=ScopeType.REQUEST, mappingType=MappingTypes.VALUE) Locale locale, 
			WebResultAction result) {
		Page page = (Page) objectsManager.getObject( "/pages" + uri, locale);
		
		if(page == null && locale != null) {
			page = (Page) objectsManager.getObject( "/pages" + uri, null);
		}
		
		if(page != null) {
			ObjectTemplate pg = objectTemplateManager.getTemplate("page", page.getTemplate());
			result.setView(pg.getTemplate(), true);
			result.setDispatcher(WebDispatcherType.FORWARD);
			result.add("page", page);
		}
		else {
			result.setResponseStatus(HttpStatus.NOT_FOUND);
		}
		
		return result;
	}
}
