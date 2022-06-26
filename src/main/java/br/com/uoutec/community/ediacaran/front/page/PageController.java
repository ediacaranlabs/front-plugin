package br.com.uoutec.community.ediacaran.front.page;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.ActionStrategy;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.web.WebActionStrategyType;
import org.brandao.brutos.web.HttpStatus;
import org.brandao.brutos.web.WebResultAction;

import br.com.uoutec.community.ediacaran.plugins.PublicType;

@Singleton
@ActionStrategy(WebActionStrategyType.DETACHED)
public class PageController implements PublicType {

	@Inject
	@Transient
	private PageManager pageManager;

	@Action("{uri:(/[a-z][a-z0-9]+(-[a-z0-9]+)*)+}")
	public WebResultAction execute(@Basic(bean="uri") String uri, @Basic(bean="locale", mappingType=MappingTypes.VALUE) Locale locale, WebResultAction result) {
		Page page = pageManager.getPage(uri, locale);
		if(page == null && locale != null) {
			page = pageManager.getPage(uri, null);
		}
		
		if(page != null) {
			result.setView("template/default");
			result.add("page", page);
		}
		else {
			result.setResponseStatus(HttpStatus.NOT_FOUND);
		}
		
		return result;
	}
}
