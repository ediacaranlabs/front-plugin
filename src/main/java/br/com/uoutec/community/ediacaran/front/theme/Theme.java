package br.com.uoutec.community.ediacaran.front.theme;

import java.io.Writer;
import java.util.List;
import java.util.Map;

public interface Theme {
	
	void buildComponent(String template, String packageName, ComponentVars componentVars, Map<String, Object> vars, Writer out) throws ThemeException;
	
	void buildComponent(String template, String packageName, Writer out, Object ... vars) throws ThemeException;
	
	String getBasePath();
	
	String getContext();
	
	String getTemplate(String packageName);
	
	List<PublicResource> getResourcesByType(String type, String packageName);
	
}
