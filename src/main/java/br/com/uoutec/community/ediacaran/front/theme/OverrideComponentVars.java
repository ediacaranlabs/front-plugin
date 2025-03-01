package br.com.uoutec.community.ediacaran.front.theme;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OverrideComponentVars implements ComponentVars{

	private Map<String, Object> vars;
	
	private ComponentVars parent;
	
	public OverrideComponentVars(Map<String, Object> vars, ComponentVars parent) {
		this.vars = vars;
		this.parent = parent;
	}
	
	public Map<String, Object> prepareVars(Map<String, PropertyParser> propertyParsers, Set<String> defaultProperties,
			Map<String, PropertyParser> attributeParsers, Set<String> emptyAttributes, Set<String> defaultAttributes){
		
		Map<String,Object> values = new HashMap<>();
		
		if(parent != null) {
			values.putAll(parent.prepareVars(propertyParsers, defaultProperties, attributeParsers, emptyAttributes, defaultAttributes));
		}
		
		values.putAll(vars);
		
		return values;
	}
	
}
