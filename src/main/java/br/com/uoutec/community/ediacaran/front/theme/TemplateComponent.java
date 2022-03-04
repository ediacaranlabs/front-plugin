package br.com.uoutec.community.ediacaran.front.theme;

import java.io.Writer;
import java.util.Map;
import java.util.Set;

public interface TemplateComponent {

	void build(Map<String,Object> vars, Writer out) throws ThemeException;
	
	void build(Writer out, Object ... vars) throws ThemeException;
	
	void loadConfiguration();
	
	void loadTemplate();
	
	String getTemplate();
	
	Set<String> getAttributes();
	
	Set<String> getEmptyAttributes();
	
	Map<String, PropertyParser> getAttributesParser();

	Set<String> getProperties();
	
	Map<String, PropertyParser> getPropertiesParser();
	
}
