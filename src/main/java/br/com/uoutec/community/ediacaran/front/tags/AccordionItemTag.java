package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import br.com.uoutec.community.ediacaran.front.StringPattern;
import br.com.uoutec.community.ediacaran.front.StringPattern.AbstractVarParser;;

public class AccordionItemTag extends EdiacaranSimpleTagSupport {

	private static final StringPattern TEMPLATE = new StringPattern(
			"<div class=\"card\">\r\n" + 
			"	<div class=\"card-header\"\r\n" + 
			"		id=\"heading{accordionID}_{count}\">\r\n" + 
			"		<a class=\"btn btn-link\" data-toggle=\"collapse\"\r\n" + 
			"			data-target=\"#collapse{accordionID}_{count}\"\r\n" + 
			"			aria-expanded=\"true\"\r\n" + 
			"			aria-controls=\"collapse{accordionID}_{count}\">\r\n" + 
			"			{title} </a>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"	<div id=\"collapse{accordionID}_{count}\"\r\n" + 
			"		class=\"collapse\"\r\n" + 
			"		aria-labelledby=\"heading{accordionID}_{count}\"\r\n" + 
			"		data-parent=\"#accordion{accordionID}\">\r\n" + 
			"		<div class=\"card-body\">\r\n" +
			"           {content}" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"</div>\r\n" 
		);

	private String title;
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Object accordionID  = this.getJspContext().getAttribute(AccordionTag.ACCORDION_ID_ATTR);
			Integer count       = (Integer) this.getJspContext().getAttribute(AccordionTag.ACCORDION_COUNT_ATTR);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("accordionID", accordionID);
			map.put("count", count);
			map.put("title", title);
			map.put("content", new AbstractVarParser() {
				
				public void parse(Writer writter) throws IOException {
					try {
						getJspBody().invoke(writter);
					}
					catch(Throwable e) {
						throw new IllegalStateException(e);
					}
				}
				
			});
			
			JspWriter out = this.getJspContext().getOut();

			TEMPLATE.toWriter(out, map);
			
			this.getJspContext().setAttribute(AccordionTag.ACCORDION_COUNT_ATTR, count.intValue() + 1);
    	}
    	catch(IllegalStateException e) {
    		throw e;
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }
	
    /*
    public void doInitBody() throws JspException {
    	try {
			Integer accordionID = (Integer) this.pageContext.getAttribute(AccordionTag.ACCORDION_ID_ATTR);
			Integer count       = (Integer) this.pageContext.getAttribute(AccordionTag.ACCORDION_COUNT_ATTR);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("accordionID", accordionID);
			map.put("count", count);
			map.put("title", title);
			
			JspWriter out = bodyContent.getEnclosingWriter();
			TEMPLATE_START.toWriter(out, map);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    }


    public int doAfterBody() throws JspException {
    	try {
			Integer i = (Integer) this.pageContext.getAttribute(AccordionTag.ACCORDION_COUNT_ATTR);
			this.pageContext.setAttribute(AccordionTag.ACCORDION_COUNT_ATTR, i.intValue() + 1);
    		
			JspWriter out = bodyContent.getEnclosingWriter();
			TEMPLATE_END.toWriter(out);
		 	return SKIP_BODY;
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    }
*/

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}	
    
}
