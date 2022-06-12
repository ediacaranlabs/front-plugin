package br.com.uoutec.community.ediacaran.front.pub;

import java.util.Locale;
import java.util.regex.Pattern;

public class ObjectPersistenceUtil {

	private Pattern keyFormat = Pattern.compile("(/[a-z]+(-[0-9a-z]))+");
	
	private Pattern typeFormat = Pattern.compile("[0-9a-z]{4,10}");
	
	public String toPath(String id, Locale locale, String type) {
		
		if(!keyFormat.matcher(id).matches()) {
			throw new IllegalStateException("invalid id: " + id);
		}
		
		if(!typeFormat.matcher(type).matches()) {
			throw new IllegalStateException("invalid type: " + type);
		}
		
		StringBuilder builder = new StringBuilder(id).append("/").append(type).append("/");
		
		if(locale == null) {
			builder.append("default.json");
		}
		else {
			builder.append(locale.getLanguage()).append("_").append(locale.getCountry()).append(".json");
		}
		
		return builder.toString();
	}

	
}
