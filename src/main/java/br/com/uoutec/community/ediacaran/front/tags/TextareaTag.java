package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class TextareaTag extends ComponentFormTag {

	public static final String TEMPLATE = "/bootstrap4/templates/components/textfield";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
			add("autocomplete");
			add("autofocus");
			add("maxlength");
			add("minlength");
			add("pattern");
			add("placeholder");
			add("readonly");
			add("required");
			add("cols");
			add("rows");
			remove("value");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("autocomplete", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && (Boolean)value? "on" : "off";
				}
			});

			put("readonly", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return null;
				}
				
				@Override
				public Object toValue(Object value) {
					return value != null && (Boolean)value? "readonly" : "";
				}
				
			});

			put("required", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return null;
				}
				
				@Override
				public Object toValue(Object value) {
					return value != null && (Boolean)value? "readonly" : "";
				}
				
			});
			
		}});
	
	/* ------------ Attr ---------------*/
	
	private Boolean autocomplete;
	
	private Boolean autofocus;

	private Integer cols;
	
	private Integer rows;
	
	private Integer maxlength;
	
	private String placeholder;
	
	private Boolean readonly;

	private Boolean required;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	public TextareaTag() {
		super.setComponentType("text");
	}
	
	@Override
	public void doInnerTag() throws JspException, IOException {
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("enabled", this.getEnabled() != null && !this.getEnabled()? " uneditable-input" : "");
			vars.put("label",   label);
			vars.put("empty",   label == null? "sr-only" : null);
			vars.put("name",    super.getName());
			vars.put("value",   super.getValue());
			vars.put("attr",    super.toAttrs());
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
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
	
	public Boolean getAutocomplete() {
		return autocomplete;
	}

	public void setAutocomplete(Boolean autocomplete) {
		this.autocomplete = autocomplete;
	}

	public Boolean getAutofocus() {
		return autofocus;
	}

	public void setAutofocus(Boolean autofocus) {
		this.autofocus = autofocus;
	}

	public Integer getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
