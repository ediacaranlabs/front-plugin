package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class AccordionTag extends BasicTag {

	public static final String TEMPLATE             = "bootstrap4/templates/components/accordion";
	
	public static final String ACCORDION_ATTR       = "_accordion";
	
	public static final String ACCORDION_COUNT_ATTR = "_accordion_count";
	
	@SuppressWarnings("serial")
	private static final Set<String> ignore = new HashSet<String>() {{
		add("id");
	}};
	
	public AccordionTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	Integer oldCount = null;
    	
    	try {
    		
    		Object accordionID = this.getId();
    		oldCount           = (Integer)this.setProperty(ACCORDION_COUNT_ATTR, 0);
			
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr",           super.toAttrs(null, ignore));
			vars.put("accordionID",    accordionID);
			vars.put("accordion-item", new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	finally {
    		if(oldCount != null) {
    			setProperty(ACCORDION_COUNT_ATTR, oldCount);
    		}
    	}
    	
    }
	
}
