package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.front.theme.AbstractVarParser;
import br.com.uoutec.community.ediacaran.front.theme.ComponentParserException;

public class EscapeVarParser extends AbstractVarParser{

	private JspFragment jspBody;
	
	public EscapeVarParser(JspFragment jspBody) {
		this.jspBody = jspBody;
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		try {
			if(jspBody != null) {
				jspBody.invoke(new EscapeWriter(writter));
			}
		}
		catch(JspException | IOException e) {
			throw new ComponentParserException(e);
		}
	}

	@Override
	public String parse() throws IOException {
		throw new UnsupportedOperationException();
	}
	
}
