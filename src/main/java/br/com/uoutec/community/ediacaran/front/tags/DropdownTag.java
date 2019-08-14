package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DropdownTag  extends ComponentFormTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/dropdown";
	
	public static final String TEMPLATE2 = "/bootstrap4/templates/components/split-dropdown";
	
	public static final String TEMPLATE_WRAPPER = "/bootstrap4/templates/components/dropdown-wrapper";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_PROPS) {{
			add("label");
			add("style");
			add("size");
			add("split");
			add("variation");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_PROPERTY_PARSERS){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return " btn-" + value;
					}
					
				});
				
				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return " btn-" + value;
					}
					
				});
				
				put("variation", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return " drop" + value;
					}
					
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String style; //primary, secondary, success, info, warning, danger
	
	private String size; //lg, sm
	
	private Boolean split;
	
	private String variation; //up, right, left
	
	public DropdownTag() {
	}
	
    protected String getWrapperTemplate() {
    	return TEMPLATE_WRAPPER;
    }
    
    protected String getDefaultTemplate() {
    	return split == null || !split? TEMPLATE : TEMPLATE2;
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
    
	public Map<String, Object> prepareVars() {
		Map<String, Object> vals = super.prepareVars();
		vals.put("itens", new JspFragmentVarParser(getJspBody()));
		return vals;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getSplit() {
		return split;
	}

	public void setSplit(Boolean split) {
		this.split = split;
	}

	public String getVariation() {
		return variation;
	}

	public void setVariation(String variation) {
		this.variation = variation;
	}
    
}
