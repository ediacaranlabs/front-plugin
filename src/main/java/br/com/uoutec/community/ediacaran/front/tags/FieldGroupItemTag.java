package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FieldGroupItemTag  extends AbstractTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/field-group-item";
	
	public static final String TEMPLATE2  = "/bootstrap4/templates/components/content";
	
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
			add("text");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String text;
	
	public FieldGroupItemTag() {
	}
	
	public Map<String, Object> getValues() {
		if(text == null) {
			Map<String, Object> vals = super.getValues();
			vals.put("content", new JspFragmentVarParser(getJspBody()));
			return vals;
		}
		else {
			return super.getValues();
		}
	}
	
    protected String getDefaultTemplate() {
    	return text == null? TEMPLATE2 : TEMPLATE;
    }

    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }

    protected Set<String> getEmptyAttributes(){
    	return DEFAULT_EMPTY_ATTRIBUTES;
    }
    
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

    protected Set<String> getDefaultProperties(){
    	return DEFAULT_PROPS;
    }

    protected Map<String, AttributeParser> getPropertyParsers(){
    	return DEFAULT_PROPERTY_PARSERS;
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    
}
