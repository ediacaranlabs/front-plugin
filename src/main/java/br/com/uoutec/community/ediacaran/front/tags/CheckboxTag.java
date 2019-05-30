package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class CheckboxTag extends AbstractTag {

	public static final String TEMPLATE = "bootstrap4/templates/components/checkbox";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
			add("name");
			add("value");
			add("selected");
			add("enabled");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(FormComponentTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("selected", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && (Boolean)value? "checked" : "";
				}
			});

			put("enabled", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && !(Boolean)value? "disabled" : "";
				}
			});
			
		}});
	
	//Attrs
	private String name;

	private String value;
	
	private Boolean selected;
	
	private Boolean enabled;
	
	//props
	
	private String label;
	
	private Boolean inline;
	
	public CheckboxTag() {
	}
	
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return ATTRIBUTE_PARSERS;
    }
    
	@Override
	public void doInnerTag() throws JspException, IOException {
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("enalbed", enabled != null && !enabled? " uneditable-input" : "");
			vars.put("inline",  inline != null && inline? " form-check-inline" : "");
			vars.put("label",   label);
			vars.put("attr",    super.toAttrs());
			
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
