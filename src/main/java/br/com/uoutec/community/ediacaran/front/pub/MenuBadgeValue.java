package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class MenuBadgeValue implements Serializable{

	private static final long serialVersionUID = 3845200547940555571L;
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
