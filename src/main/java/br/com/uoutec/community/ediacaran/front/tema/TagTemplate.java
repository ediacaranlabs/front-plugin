package br.com.uoutec.community.ediacaran.front.tema;

import java.io.Writer;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.tags.AttributeParser;

public interface TagTemplate {

	void applyTagTemplate(String template, Map<String,Object> vars, Writer out) throws TemaException;
	
	void applyTagTemplate(String template, Writer out, Object ... vars) throws TemaException;
	
	Set<String> getAttributes();
	
	Set<String> getEmptyAttributes();
	
	Map<String, AttributeParser> getAttributesParser();

	Set<String> getProperties();
	
	Map<String, AttributeParser> getPropertiesParse();
	
}
