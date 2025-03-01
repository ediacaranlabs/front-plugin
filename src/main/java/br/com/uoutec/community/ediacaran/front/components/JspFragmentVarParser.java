package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.front.theme.AbstractVarParser;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

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
		catch(JspException | IOException e) {
			if(e.getCause() instanceof ThemeException) {
				throw (ThemeException)e.getCause();
			}
			throw new IllegalStateException(e);
		}
	}

}
