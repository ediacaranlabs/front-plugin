package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.front.StringPattern.AbstractVarParser;

public class EscapeVarParser extends AbstractVarParser{

	private JspFragment jspBody;
	
	public EscapeVarParser(JspFragment jspBody) {
		this.jspBody = jspBody;
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		try {
			jspBody.invoke(new EscapeWriter(writter));
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
