package br.com.uoutec.community.ediacaran.front.theme;

import br.com.uoutec.community.ediacaran.front.tags.PropertiesComponentTemplate;

public class PropertyParserImp implements PropertyParser{

	@Override
	public String toName(String value, PropertiesComponentTemplate component) {
		return value;
	}

	@Override
	public Object toValue(Object value, PropertiesComponentTemplate component) {
		return value == null? null : String.valueOf(value);
	}

}
