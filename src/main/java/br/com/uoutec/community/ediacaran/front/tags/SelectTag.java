package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class SelectTag extends ComponentFormTag {

	public static final String TEMPLATE = "/bootstrap4/templates/components/select";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
			add("autofocus");
			add("readonly");
			add("required");
			add("multiple");
			add("sizeList");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
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
					return value != null && (Boolean)value? "required" : "";
				}
				
			});

			put("sizeList", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "size";
				}
				
			});
			
			put("multiple", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return null;
				}
				
				@Override
				public Object toValue(Object value) {
					return value != null && (Boolean)value? "multiple" : "";
				}
				
			});
			
		}});
	
	/* ------------ Attr ---------------*/
	
	private Boolean autofocus;
	
	private Boolean readonly;

	private Boolean required;
	
	private Boolean multiple;
	
	private Integer sizeList;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String size;

	public SelectTag() {
	}
	
	@Override
	public void doInnerTag() throws JspException, IOException {
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("enabled", this.getEnabled() != null && !this.getEnabled()? " uneditable-input" : "");
			vars.put("label",   label);
			vars.put("empty",   label == null? "sr-only" : null);
			vars.put("name",    super.getName());
			vars.put("size",    size != null? new String("form-control-").concat(size) : "");
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
	
	public Boolean getAutofocus() {
		return autofocus;
	}

	public void setAutofocus(Boolean autofocus) {
		this.autofocus = autofocus;
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

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getSizeList() {
		return sizeList;
	}

	public void setSizeList(Integer sizeList) {
		this.sizeList = sizeList;
	}

}
