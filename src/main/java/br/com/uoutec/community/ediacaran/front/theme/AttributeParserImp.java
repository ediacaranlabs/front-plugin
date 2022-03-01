package br.com.uoutec.community.ediacaran.front.theme;

import br.com.uoutec.community.ediacaran.front.tags.ComponentProperties;

public class AttributeParserImp implements AttributeParser{

	@Override
	public String toName(String value, ComponentProperties component) {
		return value;
	}

	@Override
	public Object toValue(Object value, ComponentProperties component) {
		return value == null? null : String.valueOf(value);
	}

}
