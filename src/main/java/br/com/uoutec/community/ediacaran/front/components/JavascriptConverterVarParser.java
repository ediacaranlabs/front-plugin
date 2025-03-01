package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.front.theme.AbstractVarParser;

public class JavascriptConverterVarParser extends AbstractVarParser{

	//public static final String WRAPPER = JavascriptConverterVarParser.class + ":active";
	
	private JspFragment jspBody;
	
	public JavascriptConverterVarParser(JspFragment jspBody) {
		this.jspBody = jspBody;
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		boolean active = JavascriptTemplateStatus.isActive();//jspBody.getJspContext().getAttribute(WRAPPER, PageContext.REQUEST_SCOPE) != null;
		try {
			if(!active) {
				if(jspBody != null) {
					JavascriptTemplateStatus.active();
					//jspBody.getJspContext().setAttribute(WRAPPER, active, PageContext.REQUEST_SCOPE);
					JavascriptConverterWriter jscw = new JavascriptConverterWriter(writter);
					jscw.start();
					jspBody.invoke(jscw);
					jscw.end();
				}
			}
		}
		catch(JspException | IOException e) {
			throw new IllegalStateException(e);
		}
		finally {
			if(!active) {
				//jspBody.getJspContext().removeAttribute(WRAPPER, PageContext.REQUEST_SCOPE);
				JavascriptTemplateStatus.deactive();
			}
		}
	}

}
