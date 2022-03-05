package br.com.uoutec.community.ediacaran.front.components;

import java.util.regex.Pattern;

public class Util {

	private static Pattern numeric = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	private static Pattern bool = Pattern.compile("true|false");
	
	public static boolean isNumeric(String strNum) {
		
	    if (strNum == null) {
	        return false; 
	    }
	    
	    return numeric.matcher(strNum).matches();
	}

	public static boolean isBoolean(String strNum) {
		
	    if (strNum == null) {
	        return false; 
	    }
	    
	    return bool.matcher(strNum).matches();
	}
	
}
