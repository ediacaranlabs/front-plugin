package br.com.uoutec.community.ediacaran.front.theme;

import br.com.uoutec.community.ediacaran.front.tags.ComponentProperties;

public interface AttributeParser {

	String toName(String value, ComponentProperties component);
	
	Object toValue(Object value, ComponentProperties component);
	
}
