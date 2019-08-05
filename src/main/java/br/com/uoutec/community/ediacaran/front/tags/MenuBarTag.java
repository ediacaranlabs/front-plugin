package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MenuBarTag  extends AbstractTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/menu-bar";
	
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
			add("style");
			add("background");
			add("position");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_PROPERTY_PARSERS){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return value == null? null : "navbar-" + value;
					}
				});
				
				put("background", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return value == null? null : "bg-" + value;
					}
				});
				
				put("position", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						switch ((String)value) {
						case "top":
						case "bottom":
							return "fixed-" + value;
						case "sticky":
							return "sticky-top";
						default:
							return value;
						}
					}
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String style; //light, dark
	
	private String background; //dark, primary
	
	private String position; //top, bottom, sticky
	
	public MenuBarTag() {
	}
	
	public Map<String, Object> getValues() {
		Map<String, Object> vals = super.getValues();
		vals.put("content", new JspFragmentVarParser(getJspBody()));
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
