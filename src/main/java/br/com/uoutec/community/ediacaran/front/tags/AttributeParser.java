package br.com.uoutec.community.ediacaran.front.tags;

public interface AttributeParser {

	String toName(String value);
	
	Object toValue(Object value);
	
}
