package br.com.uoutec.community.ediacaran.front.objects;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

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
	public void write(Object obj, OutputStream stream) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
		gson.toJson(obj, Object.class, writer);
		writer.flush();
	}

}
