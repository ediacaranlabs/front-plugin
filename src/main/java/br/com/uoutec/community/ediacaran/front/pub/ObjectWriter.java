package br.com.uoutec.community.ediacaran.front.pub;

import java.io.IOException;
import java.io.Writer;

public interface ObjectWriter {

	void write(Object obj, Writer writer) throws IOException;
	
}
