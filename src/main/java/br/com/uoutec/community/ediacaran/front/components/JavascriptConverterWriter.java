package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

public class JavascriptConverterWriter extends EscapeWriter{

	//.join("")
	
	public static final char DISABLE_PARSER = 0x0;

	public static final char ENABLE_PARSER = 0x1;
	
	//private static final String QUOTE = "\\\"";
	
	private static final String START_CONTENT = "out_.push(\"";

	private static final String END_CONTENT = "\");\r\n";

	private static final String START_CODE = "out_.push(";

	private static final String END_CODE = ");\r\n";
	
	private static final int NORMAL_CONTENT = 0;
	
	private static final int FIRST_CHAR = 1;

	private static final int JS_CONTENT = 2;

	private int status;
	
	private Writer o;
	
	private boolean first;
	
	private boolean enabled;
	
	public JavascriptConverterWriter(Writer o) {
		super(o);
		this.o = o;
		this.first = true;
		this.enabled = true;
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		
		if(first && enabled) {
			o.write(START_CONTENT);
			first = false;
		}
		
		int max   = off + len;
		int start = off;

		for(int i=off;i<max;i++) {
			
			if(cbuf[i] == '\r' || cbuf[i] == '\n') {
				cbuf[i] = ' ';
			}
			
			if(!enabled) {
				if(cbuf[i] == ENABLE_PARSER) {
					enabled = true;
					o.write(cbuf, start, i - start);
					if(status == JS_CONTENT) {
						o.write("\r\n" + START_CODE);
					}
					else {
						o.write("\r\n" + START_CONTENT);
					}
					start = i + 1;
				}
				continue;
			}
			else
			if(enabled && cbuf[i] == DISABLE_PARSER) {
				enabled = false;
				o.write(cbuf, start, i - start);
				if(status == JS_CONTENT) {
					o.write(END_CODE);
				}
				else {
					o.write(END_CONTENT);
				}
				start = i + 1;
				continue;
			}
				
			if(status == NORMAL_CONTENT && cbuf[i] == '!') {
				status = FIRST_CHAR;
			}
			else
			if(status == FIRST_CHAR) {
				
				if(cbuf[i] == '{') {
					status = JS_CONTENT;
					o.write(cbuf, start, i - start - 1);
					o.write(END_CONTENT);
					o.write(START_CODE);
				}
				else {
					o.write('!');
					o.write(cbuf[i]);
				}
				
				start = i + 1;
			}
			else
			if(status == NORMAL_CONTENT && cbuf[i] == '\"') {
				o.write(cbuf, start, i - start);
				o.write("\\\"");
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
		
		o.write(cbuf, start, max - start);		
	}

	public void end() throws IOException {
		if(!enabled) {
			o.write(END_CODE);
		}
		else {
			o.write(END_CONTENT);
		}
	}
	
}
