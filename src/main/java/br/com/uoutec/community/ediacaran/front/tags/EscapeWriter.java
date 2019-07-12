package br.com.uoutec.community.ediacaran.front.tags;

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
				write(cbuf, start, off - start, 0);
				start = off + 1;
				break;
			case '>':
				write(cbuf, start, off - start, 1);
				start = off + 1;
				break;
			case '&':
				write(cbuf, start, off - start, 2);
				start = off + 1;
				break;
			case '\'':
				write(cbuf, start, off - start, 3);
				start = off + 1;
				break;
			case '\"':
				write(cbuf, start, off - start, 4);
				start = off + 1;
				break;
			}
		}
		
		o.write(cbuf, start, len - start);
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
