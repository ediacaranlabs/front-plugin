package br.com.uoutec.community.ediacaran.front.page.pub;

import javax.resource.spi.IllegalStateException;
import javax.validation.constraints.Max;

import org.brandao.brutos.annotation.Basic;

import br.com.uoutec.pub.entity.AbstractPubEntity;

public class PageSearchPubEntity extends AbstractPubEntity<PageSearch> {

	private static final long serialVersionUID = 7674988526885634067L;

	@Basic(bean="path")
	private String path;

	@Basic(bean="locale")
	private String locale;
	
	private Integer page;
	
	@Max(100)
	private Integer resultPerPage;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getResultPerPage() {
		return resultPerPage;
	}

	public void setResultPerPage(Integer resultPerPage) {
		this.resultPerPage = resultPerPage;
	}
	
	@Override
	protected boolean isEqualId(PageSearch instance) throws Throwable {
		return false;
	}

	@Override
	protected boolean hasId(PageSearch instance) throws Throwable {
		return false;
	}

	@Override
	protected PageSearch reloadEntity() throws Throwable {
		throw new IllegalStateException();
	}

	@Override
	protected void throwReloadEntityFail() throws Throwable {
	}

	@Override
	protected PageSearch createNewInstance() throws Throwable {
		return new PageSearch();
	}

	@Override
	protected void copyTo(PageSearch o, boolean reload, boolean override,
			boolean validate) throws Throwable {
		o.setLocale(locale);
		o.setPath(path);
	}
	
}
