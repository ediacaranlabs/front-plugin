package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.Serializable;

public class LocaleResponse implements Serializable{

	private static final long serialVersionUID = -3049118765824679386L;

	private String id;
	
	private String name;

	public LocaleResponse(String id, String name) {
		this.id = id;
		this.name = name;
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
	
}
