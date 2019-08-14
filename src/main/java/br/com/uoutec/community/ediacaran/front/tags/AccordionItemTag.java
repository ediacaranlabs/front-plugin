package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;;

public class AccordionItemTag extends AbstractTag {

	public static final String TEMPLATE = "/bootstrap4/templates/components/accordion-item";

	public static final String ACCORDION_COUNT_ATTR = AccordionItemTag.class.getSimpleName();
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_PROPS) {{
			add("title");
			add("count");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private int count;
	
	private String title;
	
	public AccordionItemTag() {
	}
	
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
