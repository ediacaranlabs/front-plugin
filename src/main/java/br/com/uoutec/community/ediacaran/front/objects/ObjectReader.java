package br.com.uoutec.community.ediacaran.front.objects;

import java.io.IOException;
import java.io.InputStream;

public interface ObjectReader {

	Object read(InputStream stream) throws IOException;
	
}
