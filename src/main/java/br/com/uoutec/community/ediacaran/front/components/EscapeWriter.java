package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

public class EscapeWriter extends Writer{

	private static final char[][] tab = 
			new char[][] {
				"&lt;".toCharArray(), // <
				"&gt;".toCharArray(), // >
				"&amp;".toCharArray(), // &
				"&apos;".toCharArray(), //'
				"&quot;".toCharArray(), //"	
			};
			
	private Writer o;
	
	public EscapeWriter(Writer o) {
		this.o = o;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		int max   = off + len;
		int start = off;
		for(int i=off;i<max;i++) {
			switch (cbuf[i]) {
			case '<':
				write(cbuf, start, i - start, 0);
				start = i + 1;
				break;
			case '>':
				write(cbuf, start, i - start, 1);
				start = i + 1;
				break;
			case '&':
				write(cbuf, start, i - start, 2);
				start = i + 1;
				break;
			case '\'':
				write(cbuf, start, i - start, 3);
				start = i + 1;
				break;
			case '\"':
				write(cbuf, start, i - start, 4);
				start = i + 1;
				break;
			}
		}
		
		o.write(cbuf, start, max - start);
	}

	private void write(char[] cbuf, int off, int len, int index) throws IOException {
		o.write(cbuf, off, len);
		o.write(tab[index]);
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
