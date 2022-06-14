package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Reader;
import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.uoutec.community.ediacaran.core.system.util.DataUtil.ClassTypeAdapter;

public class ObjectReaderImp implements ObjectReader{

	private static final Gson gson;
	
	static{
		gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.TRANSIENT | Modifier.STATIC)
        .registerTypeAdapter(Class.class, new ClassTypeAdapter())
        .create();		
	}
	
	@Override
	public Object read(Reader reader) {
		return  gson.fromJson(reader, Object.class);
	}

}
