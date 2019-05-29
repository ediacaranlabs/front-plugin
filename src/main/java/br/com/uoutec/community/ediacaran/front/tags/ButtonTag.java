package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

public class ButtonTag extends AbstractTag {

	public static final String TEMPLATE = "bootstrap4/templates/components/button";
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(FormComponentTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("actionType", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "type";
				}
			});

		}});
		
	private String label;
	
	private String size;
	
	private String type;
	
	private Boolean block;
	
	private Boolean enabled;
	
	private String actionType; //submit
	
	private Boolean outline;

	public ButtonTag() {
	}
	
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return ATTRIBUTE_PARSERS;
    }
    
	@Override
	public void doInnerTag() throws JspException, IOException {
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("size",    size == null? "" : new String(" btn-").concat(size));
			
			vars.put("type",    new String(" btn-").concat(outline != null && outline ? "outline-" : "").concat(type == null? "primary" : type));
			
			vars.put("block",   block == null? "" : block? " btn-block" : "");
			
			vars.put("enabled", enabled != null && !enabled? " disabled" : "");
			
			vars.put("attr",    super.toAttrs());
			
			vars.put("label",   label);
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
