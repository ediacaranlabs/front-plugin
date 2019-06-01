package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class ButtonTag extends ComponentFormTag {

	public static final String TEMPLATE = "/bootstrap4/templates/components/button";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
			add("action");
			add("ctype");
			add("method");
			add("target");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("actionType", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "type";
				}
			});

			put("action", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "formaction";
				}
			});

			put("ctype", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "formenctype";
				}
			});

			put("method", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "formmethod";
				}
			});

			put("target", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "formtarget";
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
	
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }
    
    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }
    
	@Override
	public void doInnerTag() throws JspException, IOException {
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("size",    size == null? "" : new String(" btn-").concat(size));
			
			vars.put("type",    new String(" btn-").concat(outline != null && outline ? "outline-" : "").concat(type == null? "primary" : type));
			
			vars.put("block",   block == null? "" : block? " btn-block" : "");
			
			vars.put("enabled", this.getEnabled() != null && !this.getEnabled()? " disabled" : "");
			
			vars.put("attr",    super.toAttrs());
			
			vars.put("label",   label);
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
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
