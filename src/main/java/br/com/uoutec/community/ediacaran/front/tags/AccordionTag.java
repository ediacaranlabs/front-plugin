package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.StringPattern;

public class AccordionTag extends EdiacaranSimpleTagSupport {

	public static final String ACCORDION_ID_ATTR    = "_accordion_id";
	
	public static final String ACCORDION_COUNT_ATTR = "_accordion_count";
	
	private static final StringPattern TEMPLATE_BEFORE = new StringPattern(
			"<div {attr} id=\"accordion{accordionID}\">\r\n"
		);
	
	private static final StringPattern TEMPLATE_AFTER = new StringPattern(
		"</div>\r\n" 
	);
	
	public AccordionTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	Integer oldCount = null;
    	try {
			Integer accordionID = (Integer) this.getJspContext().getAttribute(ACCORDION_ID_ATTR);
			this.getJspContext().setAttribute(ACCORDION_ID_ATTR, accordionID = accordionID == null? 0 : accordionID.intValue() + 1);
			
			oldCount = (Integer) this.getJspContext().getAttribute(ACCORDION_COUNT_ATTR);
			this.getJspContext().setAttribute(ACCORDION_COUNT_ATTR, 0);
			
			JspWriter out = this.getJspContext().getOut();			
			TEMPLATE_BEFORE.toWriter(out, super.toAttrs(null), accordionID);
			getJspBody().invoke(out);
			TEMPLATE_AFTER.toWriter(out);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	finally {
    		if(oldCount != null) {
    			this.getJspContext().setAttribute(ACCORDION_COUNT_ATTR, oldCount);
    		}
    	}
    	
    }
	
	/*
    public void doInitBody() throws JspException {
    	try {
			Integer i = (Integer) this.pageContext.getAttribute(ACCORDION_ID_ATTR);
			this.pageContext.setAttribute(ACCORDION_ID_ATTR,    i = i == null? 0 : i.intValue() + 1);
			this.pageContext.setAttribute(ACCORDION_COUNT_ATTR, 0);
			
			JspWriter out = bodyContent.getEnclosingWriter();
			TEMPLATE_BEFORE.toWriter(out, super.toAttrs(null), i);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    }


    public int doAfterBody() throws JspException {
    	try {
			JspWriter out = bodyContent.getEnclosingWriter();
			TEMPLATE_AFTER.toWriter(out);
		 	return SKIP_BODY;
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    }
	*/
}
