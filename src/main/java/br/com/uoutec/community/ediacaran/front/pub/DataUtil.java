package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Reader;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.uoutec.community.ediacaran.system.util.DataUtil.ClassTypeAdapter;

public class DataUtil {

	private static final Gson gson;
	
	static{
		gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.TRANSIENT | Modifier.STATIC)
        .registerTypeAdapter(Class.class, new ClassTypeAdapter())
        .create();		
	}
	
	public static String encode(Map<?,?> o) {
		return gson.toJson(o);
	}
	
	public static Map<?,?> decode(String o) {
		return (Map<?,?>)gson.fromJson(o, Object.class);
	}

	public static Map<?,?> decode(Reader value) {
		return (Map<?,?>)gson.fromJson(value, Object.class);
	}
	
}
