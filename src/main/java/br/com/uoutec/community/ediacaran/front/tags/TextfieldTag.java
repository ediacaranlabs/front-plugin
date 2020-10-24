package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class TextfieldTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/textfield";
	
	/* ------------ Attr ---------------*/
	
	private Boolean autocomplete;
	
	private Boolean autofocus;

	private Integer maxlength;
	
	private Integer minlength;
	
	private String pattern;
	
	private String placeholder;
	
	private Boolean required;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String size;

	private JspFragmentVarParser content;
	
	public TextfieldTag() {
		super.setComponentType("text");
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
		vars.put("empty", label == null? "sr-only" : null);
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
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

	public Integer getMinlength() {
		return minlength;
	}

	public void setMinlength(Integer minlength) {
		this.minlength = minlength;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
