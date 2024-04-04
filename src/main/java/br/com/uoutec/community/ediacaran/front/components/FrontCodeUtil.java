package br.com.uoutec.community.ediacaran.front.components;

public class FrontCodeUtil {

	public static boolean isCode(String value) {
		return value.startsWith("!{") && value.endsWith("}");
	}
	
	public static String toRequiredCode(String value) {
		if(value == null) {
			throw new IllegalArgumentException("value");
		}
		
		if(value.startsWith("!{") && value.endsWith("}")) {
			return value.substring(2, value.length() - 1);
		}
		
		throw new IllegalArgumentException("value");
	}

	public static String toSimpleCode(String value) {
		
		if(value == null) {
			throw new IllegalArgumentException("value");
		}
		
		if(value.startsWith("!{") && value.endsWith("}")) {
			throw new IllegalArgumentException("value");
		}

		return value;
	}
	
	public static String toStringCode(String value) {
		if(value == null) {
			return null;
		}
		
		return "\"" + value + "\"";
	}
	
	public static String toCode(String value) {
		if(value == null) {
			return null;
		}
		
		if(value.startsWith("!{") && value.endsWith("}")) {
			return value.substring(2, value.length() - 1);
		}
		
		return "\"" + value + "\"";
	}
	
}
