package br.com.uoutec.community.ediacaran.front.objects;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.uoutec.community.ediacaran.core.system.util.DataUtil.ClassTypeAdapter;

public class ObjectWriterImp implements ObjectWriter{

	private static final Gson gson;
	
	static{
		gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.TRANSIENT | Modifier.STATIC)
        .registerTypeAdapter(Class.class, new ClassTypeAdapter())
        .create();		
	}
	
	@Override
	public void write(Object obj, Writer writer) throws IOException {
		gson.toJson(obj, Object.class, writer);
	}

}
