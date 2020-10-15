package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class ButtonTag extends ComponentFormTag {

	public static final String TEMPLATE = "/bootstrap4/components/button";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
			add("action");
			add("ctype");
			add("method");
			add("target");
			add("actionType");
			remove("classStyle");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("actionType", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "type";
				}
			});

			put("action", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formaction";
				}
			});

			put("ctype", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formenctype";
				}
			});

			put("method", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formmethod";
				}
			});

			put("target", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formtarget";
				}
			});
			
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_PROPS) {{
			add("label");
			add("size");
			add("type");
			add("block");
			add("outline");
			add("classStyle");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_PROPERTY_PARSERS){{
				
				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "" : new String(" btn-").concat(String.valueOf(value));
					}
				});

				put("type", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean outline = ((ButtonTag)component).getOutline();
						return new String(" btn-").concat(outline != null && outline ? "outline-" : "").concat(value == null? "primary" : String.valueOf(value));
					}
				});
				
				put("block", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "" : (Boolean)value? " btn-block" : "";
					}
				});
				
				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((ButtonTag)component).getEnabled();
						return enabled != null && !enabled? " disabled" : "";
					}
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	private String action;
	
	private String ctype;
	
	private String method;
	
	private String target;

	private String actionType; //submit
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String size;
	
	private String type;
	
	private Boolean block;
	
	private Boolean outline;

	public ButtonTag() {
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
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getOutline() {
		return outline;
	}

	public void setOutline(Boolean outline) {
		this.outline = outline;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
