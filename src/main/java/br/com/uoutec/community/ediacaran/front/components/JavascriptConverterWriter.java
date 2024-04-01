package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

public class JavascriptConverterWriter extends EscapeWriter{

	private static final int FIRST_CHAR = 1;

	private static final int JS_CONTENT = 2;

	private static final int NORMAL_CONTENT = 0;
	
	private int status;
	
	private Writer o;
	
	private int count;
	public JavascriptConverterWriter(Writer o) {
		super(o);
		this.o = o;
		this.count = 0;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		
		if(count++ == 0) {
			o.write("\"");
		}
		
		int max   = off + len;
		int start = off;

		for(int i=off;i<max;i++) {
			
			if(status == NORMAL_CONTENT && cbuf[i] == '#') {
				status = FIRST_CHAR;
			}
			else
			if(status == FIRST_CHAR) {
				
				if(cbuf[i] == '{') {
					status = JS_CONTENT;
					super.write(cbuf, start, i - start - 1);
					o.write("\" + \r\n");
				}
				else {
					o.write('#');
					o.write(cbuf[i]);
				}
				
				start = i + 1;
			}
			else
			if(status == JS_CONTENT && cbuf[i] == '}') {
				status = NORMAL_CONTENT;
				o.write(cbuf, start, i - start);
				o.write(" + \r\n\"");
				start = i + 1;
			}
				
		}
		
		super.write(cbuf, start, max - start);		
	}

	public void close() throws IOException {
		o.write("\";");
		o.flush();
		super.close();
	}
	
}
