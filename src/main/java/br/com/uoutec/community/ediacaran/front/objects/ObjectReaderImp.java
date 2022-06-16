package br.com.uoutec.community.ediacaran.front.objects;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;

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
	public Object read(InputStream stream) throws IOException {
		try(Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)){
			return  gson.fromJson(reader, Object.class);
		}
	}

}
