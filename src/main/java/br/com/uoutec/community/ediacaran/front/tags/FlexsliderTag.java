package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class FlexsliderTag  extends AbstractTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/flexslider";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
			//add("");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
			/*
			put("", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value;
				}
				
				@Override
				public Object toValue(Object value) {
					return value;
				}
				
			});
			*/
			
		}});

	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public FlexsliderTag() {
	}
	
    public void doInnerTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr", super.toAttrs());
			vars.put("imgs", new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(
						this.getTemplate() == null? 
								TEMPLATE : 
								this.getTemplate(), 
						vars, getJspContext().getOut()
				);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }
	
}
