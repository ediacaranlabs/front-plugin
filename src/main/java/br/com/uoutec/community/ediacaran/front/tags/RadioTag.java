package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RadioTag extends ComponentFormTag {

	public static final String TEMPLATE = "/bootstrap4/templates/components/radio";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
			add("selected");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("selected", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "checked" : "";
				}
			});
			
		}});
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_PROPS) {{
			add("label");
			add("inline");
			add("enabled");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_PROPERTY_PARSERS){{
				put("enalbed", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((RadioTag)component).getEnabled();
						return enabled != null && !enabled? " uneditable-input" : "";
					}
					
				});
				
				put("inline", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value != null && (Boolean)value? " form-check-inline" : "";
					}
					
				});
				
				put("label", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? new JspFragmentVarParser(((RadioTag)component).getJspBody()) : value;
					}
					
				});
			}});
	
	/* ------------ Attr ---------------*/
	
	private Boolean selected;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private Boolean inline;
	
	public RadioTag() {
		setComponentType("radio");
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
	
	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getInline() {
		return inline;
	}

	public void setInline(Boolean inline) {
		this.inline = inline;
	}

}
