package br.com.uoutec.community.ediacaran.front;

import java.io.Writer;
import java.util.Map;

import org.brandao.brutos.io.ResourceLoader;

@Deprecated
public interface TemplatesManager {

	StringPattern getTemplate(String template);
	
	void apply(String template, Map<String,Object> vars, Writer out) throws TemplatesManagerException;

	void apply(String template, Writer out, Object ... vars) throws TemplatesManagerException;
	
	TemplateLoader getTemplateLoader();

	ResourceLoader getLoader();

	String getCharset();
	
}
