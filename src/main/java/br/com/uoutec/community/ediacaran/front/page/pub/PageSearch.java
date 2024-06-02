package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.Serializable;

public class PageSearch implements Serializable {

	private static final long serialVersionUID = 7674988526885634067L;

	private String path;

	private String locale;

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
	
}
