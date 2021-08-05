package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.front.theme.AbstractVarParser;

public class JspFragmentVarParser extends AbstractVarParser{

	private JspFragment jspBody;
	
	public JspFragmentVarParser(JspFragment jspBody) {
		this.jspBody = jspBody;
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		try {
			if(jspBody != null) {
				jspBody.invoke(writter);
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
