package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;

public class MenubarSearchResponse implements Serializable {

	private static final long serialVersionUID = 7674988526885634067L;

	private int index;
	
	private String id;
	
	private String name;
	
	private String path;
	
	private LocaleResponse locale;
	
	private Integer page;
	
	private Integer resultPerPage;
	
	public MenubarSearchResponse() {
	}
	
	public MenubarSearchResponse(int index, ObjectMetadata e, Map<Locale, String> localeMap) {
		this.index = index;
		this.id = e.getPathMetadata().getId();
		this.name = e.getPathMetadata().getPath() + "/" + e.getPathMetadata().getId();
		this.path = e.getPathMetadata().getPath();
		this.locale = e.getLocale() == null? new LocaleResponse("", "") : new LocaleResponse(e.getLocale().toString(), localeMap.get(e.getLocale()));
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public LocaleResponse getLocale() {
		return locale;
	}

	public void setLocale(LocaleResponse locale) {
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
	
}
