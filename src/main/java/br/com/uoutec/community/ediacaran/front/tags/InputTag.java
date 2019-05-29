package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class InputTag extends FormComponentTag{

	public static final String ID_COUNT   = "_component_id_count";

	@SuppressWarnings("serial")
	private static final Set<String> DEFAULT_ATTRS = new HashSet<String>(FormComponentTag.DEFAULT_ATTRS) {{
		add("max");
		add("maxlength");
		add("min");
		add("pattern");
		add("readonly");
		add("required");
		add("size");
		add("step");
		add("name");
		add("accept");
		add("alt");
		add("checked");
		add("src");
		add("autocomplete");
		add("height");
		add("width");
		add("list");
		add("multiple");
		add("placeholder");
	}};
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(FormComponentTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("enabled", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && !((Boolean)value)? "disabled" : "";
				}
			});

			put("readonly", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && ((Boolean)value)? "readonly" : "";
				}
			});

			put("required", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && ((Boolean)value)? "required" : "";
				}
			});

			put("autocomplete", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value == null? "" : ((Boolean)value)? "on" : "off";
				}
			});

			put("autofocus", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && ((Boolean)value)? "autofocus" : "";
				}
			});

			put("multiple", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && ((Boolean)value)? "multiple" : "";
				}
			});
			
		}});
	
	private Boolean enabled; //disabled
	
	private String max;
	
	private Integer maxlength;
	
	private String min;
	
	private String pattern;
	
	private Boolean readonly; //readonly
	
	private String required; //required
	
	private Integer size;
	
	private Integer step;
	
	private String value;
	
	private String name;
	
	private String type;

	private String accept;
	
	private String alt;
	
	private Boolean checked;

	private String src;
	
	private Boolean autocomplete; //on off
	
	private Boolean autofocus; //autofocus
	
	private String form;
	
	private String formaction;
	
	private String formenctype;
	
	private String formmethod;
	
	private Boolean formnovalidate;
	
	private String formtarget;
	
	private Integer height;
	
	private Integer width;
	
	private String list;
	
	private Boolean multiple; //multiple
	
	private String placeholder;
	
    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }
	
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public Integer getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getFormaction() {
		return formaction;
	}

	public void setFormaction(String formaction) {
		this.formaction = formaction;
	}

	public String getFormenctype() {
		return formenctype;
	}

	public void setFormenctype(String formenctype) {
		this.formenctype = formenctype;
	}

	public String getFormmethod() {
		return formmethod;
	}

	public void setFormmethod(String formmethod) {
		this.formmethod = formmethod;
	}

	public Boolean getFormnovalidate() {
		return formnovalidate;
	}

	public void setFormnovalidate(Boolean formnovalidate) {
		this.formnovalidate = formnovalidate;
	}

	public String getFormtarget() {
		return formtarget;
	}

	public void setFormtarget(String formtarget) {
		this.formtarget = formtarget;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
    
}
