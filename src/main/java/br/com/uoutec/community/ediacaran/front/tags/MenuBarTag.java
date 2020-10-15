package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class MenuBarTag  extends AbstractSimpleTag {

	public static final String CONTEXT_ID = MenuBarTag.class.getName() + ":CONTEXT";
	
	public static final String TEMPLATE  = "/bootstrap4/components/menu-bar";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_PROPS) {{
			add("style");
			add("background");
			add("position");
			add("content");
			add("expand");
			add("sidebar");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? null : "navbar-" + value;
					}
				});
				
				put("background", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? null : "bg-" + value;
					}
				});
				
				put("position", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						
						if(value == null) {
							return null;
						}
						
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

				put("expand", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "navbar-expand-lg" : "navbar-expand-" + value;
					}
					
				});

				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((MenuBarTag)component).getJspBody());
					}
					
				});
				
				put("sidebar", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "" : "sidebar";
					}
					
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean sidebar;
	
	private String style; //light, dark
	
	private String background; //dark, primary
	
	private String position; //top, bottom, sticky
	
	private String expand; //sm, md, lg, xl
	
	private JspFragmentVarParser content;
	
	public MenuBarTag() {
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

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Boolean getSidebar() {
		return sidebar;
	}

	public void setSidebar(Boolean sidebar) {
		this.sidebar = sidebar;
	}

}
