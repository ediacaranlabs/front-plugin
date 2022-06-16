package br.com.uoutec.community.ediacaran.front.objects;

import java.io.IOException;
import java.io.OutputStream;

public interface ObjectWriter {

	void write(Object obj, OutputStream stream) throws IOException;
	
}
