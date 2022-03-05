package br.com.uoutec.community.ediacaran.front.theme;

import br.com.uoutec.community.ediacaran.front.components.PropertiesComponentTemplate;

public interface PropertyParser {

	String toName(String value, PropertiesComponentTemplate component);
	
	Object toValue(Object value, PropertiesComponentTemplate component);
	
}
