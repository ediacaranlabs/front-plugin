package br.com.uoutec.community.ediacaran.front.page.pub;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.ActionStrategy;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.DefaultThrowSafe;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.Result;
import org.brandao.brutos.annotation.ScopeType;
import org.brandao.brutos.annotation.Transient;
import org.brandao.brutos.annotation.View;
import org.brandao.brutos.annotation.web.WebActionStrategyType;

import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageManager;

@Singleton
@Controller
@ActionStrategy(WebActionStrategyType.DETACHED)
@DefaultThrowSafe(rendered=false)
public class DefaultPageViewController {

	@Transient
	@Inject
	public PageManager editPage;

	@Action("/pages/default")
	@View(value="${plugins.ediacaran.front.web_path}:/templates/default_template/pages/default-template")
	@Result("page")
	public Page edit(
			@Basic(bean = "page", scope = ScopeType.REQUEST, mappingType = MappingTypes.VALUE)Page page){
		return page;
	}
	
}
