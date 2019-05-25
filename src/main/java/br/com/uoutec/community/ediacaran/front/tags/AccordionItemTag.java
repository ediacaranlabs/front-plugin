package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;;

public class AccordionItemTag extends EdiacaranSimpleTagSupport {

	public static final String TEMPLATE = "bootstrap4/templates/components/accordion-item";

	private String title;
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Object accordionID  = this.getProperty(AccordionTag.ACCORDION_ATTR);
			Integer count       = (Integer)this.getProperty(AccordionTag.ACCORDION_COUNT_ATTR);
			
			Map<String,Object> vars = new HashMap<String,Object>();
			vars.put("accordionID", accordionID);
			vars.put("count",       count);
			vars.put("title",       title);
			vars.put("content",     new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
			
			this.setProperty(AccordionTag.ACCORDION_COUNT_ATTR, count.intValue() + 1);
    	}
    	catch(IllegalStateException e) {
    		throw e;
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}	
    
}
