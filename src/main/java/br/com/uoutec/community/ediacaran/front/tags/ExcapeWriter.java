package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class ExcapeWriter extends Writer{

	private Writer o;
	
	public ExcapeWriter(Writer o) {
		this.o = o;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		int max   = off + len;
		int start = -1;
		for(int i=off;i<max;i++) {
			char c = cbuf[i];
			if(c ^ '<' == 0)
		}
	}

	@Override
	public void flush() throws IOException {
		o.flush();
	}

	@Override
	public void close() throws IOException {
		o.close();
	}

}
