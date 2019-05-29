package br.com.uoutec.community.ediacaran.front.tags;

public class AttributeParserImp implements AttributeParser{

	@Override
	public String toName(String value) {
		return value;
	}

	@Override
	public Object toValue(Object value) {
		return value == null? null : String.valueOf(value);
	}

}
