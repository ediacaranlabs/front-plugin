package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class MenuBadgeValue implements Serializable{

	private static final long serialVersionUID = 3845200547940555571L;
	
	private Map<String, String> values = new HashMap<String, String>();

	public String getValue(String menuID) {
		return values.get(menuID);
	}

	public void setValue(String menuID, String value) {
		values.put(menuID, value);
	}
	
}
