package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrettifyTag  extends AbstractTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/prettify";
	
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
			add("linenums");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_PROPERTY_PARSERS){{
				put("linenums", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return value != null && (Boolean)value? " linenums" : "";
					}
					
				});
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean linenums;
	
	public PrettifyTag() {
	}
	
	public Map<String, Object> getValues() {
		Map<String, Object> vals = super.getValues();
		vals.put("content", new EscapeVarParser(getJspBody()));
		return vals;
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
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

	public Boolean getLinenums() {
		return linenums;
	}

	public void setLinenums(Boolean linenums) {
		this.linenums = linenums;
	}
    
}