package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

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
				public String toName(String value) {
					return null;
				}
				
				@Override
				public Object toValue(Object value) {
					return value != null && (Boolean)value? "checked" : "";
				}
			});
			
		}});
	
	/* ------------ Attr ---------------*/
	
	private Boolean selected;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private Boolean inline;
	
	public RadioTag() {
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
			
			vars.put("enalbed", this.getEnabled() != null && !this.getEnabled()? " uneditable-input" : "");
			vars.put("inline",  inline != null && inline? " form-check-inline" : "");
			vars.put("label",   label == null? new JspFragmentVarParser(getJspBody()) : label);
			vars.put("attr",    super.toAttrs());
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
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
