package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

public class CodeEscapeWriter extends Writer{

	private Writer o;
	
	public CodeEscapeWriter(Writer o) {
		this.o = o;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		int max   = off + len;
		int start = off;
		
		for(int i=off;i<max;i++) {
			
			if(cbuf[i] == '-') {
				o.write(cbuf, start, i - start);
				o.write("\\-");
				start = i + 1;
			}
			else
			if(cbuf[i] == '\\') {
				o.write(cbuf, start, i - start);
				o.write("\\\\");
				start = i + 1;
			}
			if(cbuf[i] == '<') {
				o.write(cbuf, start, i - start);
				o.write("&lt;");
				start = i + 1;
			}
			else
			if(cbuf[i] == '>') {
				o.write(cbuf, start, i - start);
				o.write("&gt;");
				start = i + 1;
			}
			else
			if(cbuf[i] == '&') {
				o.write(cbuf, start, i - start);
				o.write("&amp;");
				start = i + 1;
			}
			

/*
			else
			if(cbuf[i] == '&') {
				o.write(cbuf, start, i - start);
				o.write("&amp;");
				start = i + 1;
			}
*/
			/*
			else
			if(cbuf[i] == '&') {
				o.write(cbuf, start, i - start);
				o.write("&amp;");
				start = i + 1;
			}
			*/
			
			/*
			if(cbuf[i] == '\\') {
				o.write(cbuf, start, i - start);
				o.write("\\\\");
				start = i + 1;
			}
			else
			if(cbuf[i] == '<') {
				o.write(cbuf, start, i - start);
				o.write("\\<");
				start = i + 1;
			}
			else
			if(cbuf[i] == '>') {
				o.write(cbuf, start, i - start);
				o.write("\\>");
				start = i + 1;
			}
			*/
		}
		
		o.write(cbuf, start, max - start);
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
