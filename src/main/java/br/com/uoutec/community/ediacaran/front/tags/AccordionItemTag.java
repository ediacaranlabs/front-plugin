package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;;

public class AccordionItemTag extends BasicTag {

	public static final String TEMPLATE = "bootstrap4/templates/components/accordion-item";

	public static final String ACCORDION_COUNT_ATTR = AccordionItemTag.class.getSimpleName();
	
	private String title;
	
    public void doTag() throws JspException, IOException{
    	
    	try {
    		AccordionTag parent = (AccordionTag)getProperty(PARENT_TAG);
			Integer count       = (Integer)this.getProperty(ACCORDION_COUNT_ATTR);
			this.setProperty(ACCORDION_COUNT_ATTR, count = count == null? 0 : count.intValue() + 1);
			
			Map<String,Object> vars = new HashMap<String,Object>();
			vars.put("count",       count);
			vars.put("title",       title);
			vars.put("accordionID", parent.getId());
			vars.put("content",     new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
			
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
