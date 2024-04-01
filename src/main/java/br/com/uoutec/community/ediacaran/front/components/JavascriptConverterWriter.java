package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

public class JavascriptConverterWriter extends EscapeWriter{

	//.join("")
	
	private static final String START_CONTENT = "out_.push(\"";

	private static final String END_CONTENT = "\");\r\n";

	private static final String START_CODE = "out_.push(";

	private static final String END_CODE = ");\r\n";
	
	private static final int FIRST_CHAR = 1;

	private static final int JS_CONTENT = 2;

	private static final int NORMAL_CONTENT = 0;
	
	private int status;
	
	private Writer o;
	
	private boolean first;
	
	public JavascriptConverterWriter(Writer o) {
		super(o);
		this.o = o;
		this.first = true;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		
		if(first) {
			o.write(START_CONTENT);
			first = false;
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
					o.write(END_CONTENT);
					o.write(START_CODE);
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
				o.write(END_CODE);
				o.write(START_CONTENT);
				start = i + 1;
			}
				
		}
		
		super.write(cbuf, start, max - start);		
	}

	public void close() throws IOException {
		o.write(END_CONTENT);
		o.flush();
		super.close();
	}
	
}
