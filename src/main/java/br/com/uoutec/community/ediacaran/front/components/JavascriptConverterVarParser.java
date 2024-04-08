package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.front.theme.AbstractVarParser;

public class JavascriptConverterVarParser extends AbstractVarParser{

	public static final String WRAPPER = JavascriptConverterVarParser.class + ":active";
	
	private JspFragment jspBody;
	
	public JavascriptConverterVarParser(JspFragment jspBody) {
		this.jspBody = jspBody;
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		try {
			if(jspBody != null) {
				JavascriptConverterWriter jscw = new JavascriptConverterWriter(writter);
				
				Boolean active = (Boolean)jspBody.getJspContext().getAttribute(WRAPPER);
				
				if(active == null || !active.booleanValue()) {
					active = false;
					jspBody.getJspContext().setAttribute(WRAPPER, Boolean.TRUE);
				}
				
				try {
					jscw.start();
					jspBody.invoke(jscw);
					jscw.end();
				}
				finally {
					if(!active) {
						jspBody.getJspContext().removeAttribute(WRAPPER);
					}
				}
			}
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String parse() throws IOException {
		throw new UnsupportedOperationException();
	}

}
