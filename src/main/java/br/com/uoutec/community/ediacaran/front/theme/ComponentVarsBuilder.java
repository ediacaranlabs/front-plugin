package br.com.uoutec.community.ediacaran.front.theme;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ComponentVarsBuilder implements ComponentVars{

	public Map<String, Object> vars;
	
	public ComponentVarsBuilder() {
		this.vars = new HashMap<String, Object>();
	}
	
	public Map<String, Object> prepareVars(Map<String, PropertyParser> propertyParsers, Set<String> defaultProperties,
			Map<String, PropertyParser> attributeParsers, Set<String> emptyAttributes, Set<String> defaultAttributes){
		return vars;
	}
	
	public ComponentVarsBuilder put(String name, Object value) {
		vars.put(name, value);
		return this;
	}
	
	public ComponentVarsBuilder remove(String name) {
		vars.remove(name);
		return this;
	}
	
}
