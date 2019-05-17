package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import br.com.uoutec.community.ediacaran.front.StringPattern;
import br.com.uoutec.community.ediacaran.front.StringPattern.AbstractVarParser;

public class AccordionTag extends EdiacaranSimpleTagSupport {

	public static final String ACCORDION_ID_ATTR    = "_accordion_id";
	
	public static final String ACCORDION_ID_COUNT    = "_accordion_id_count";
	
	public static final String ACCORDION_COUNT_ATTR = "_accordion_count";
	
	private static final StringPattern TEMPLATE = new StringPattern(
			"<div {attr} id=\"accordion{accordionID}\">\r\n" + 
			"	{content}\r\n" +
			"</div>\r\n" 
	);
	
	@SuppressWarnings("serial")
	private static final Set<String> ignore = new HashSet<String>() {{
		add("id");
	}};
	
	public AccordionTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	Integer oldCount = null;
    	
    	try {
    		
    		Object accordionID;
    		
    		if(this.getId() == null) {
				Integer acc = (Integer) this.getJspContext().getAttribute(ACCORDION_ID_COUNT);
				this.getJspContext().setAttribute(ACCORDION_ID_COUNT, accordionID = acc == null? 0 : acc.intValue() + 1);
    		}
    		else {
    			accordionID = this.getId();
    		}
    		
			this.getJspContext().setAttribute(ACCORDION_ID_ATTR, accordionID);
    		
			oldCount = (Integer) this.getJspContext().getAttribute(ACCORDION_COUNT_ATTR);
			this.getJspContext().setAttribute(ACCORDION_COUNT_ATTR, 0);
			
			JspWriter out = this.getJspContext().getOut();
			
			TEMPLATE.toWriter(
					out, 
					super.toAttrs(null, ignore), 
					accordionID,
					new AbstractVarParser() {
						
						public void parse(Writer writter) throws IOException {
							try {
								getJspBody().invoke(writter);
							}
							catch(Throwable e) {
								throw new IllegalStateException(e);
							}
						}
						
					}
				);
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
