package br.com.uoutec.community.ediacaran.front.tema;

import java.io.Writer;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.tags.AttributeParser;

public interface Tema {
	
	void applyTagTemplate(String template, Map<String,Object> vars, Writer out) throws TemaException;
	
	void applyTagTemplate(String template, Writer out, Object ... vars) throws TemaException;
	
	String getContext();
	
	String getBase();
	
	Set<String> getAttributes(Object tag);
	
	Set<String> getEmptyAttributes(Object tag);
	
	Map<String, AttributeParser> getAttributesParser(Object tag);

	Set<String> getProperties(Object tag);
	
	Map<String, AttributeParser> getPropertiesParse(Object tag);
	
}
