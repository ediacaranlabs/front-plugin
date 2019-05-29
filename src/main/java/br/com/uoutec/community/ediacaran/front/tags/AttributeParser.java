package br.com.uoutec.community.ediacaran.front.tags;

public interface AttributeParser {

	Object toName(String value);
	
	Object toValue(Object value);
	
}
