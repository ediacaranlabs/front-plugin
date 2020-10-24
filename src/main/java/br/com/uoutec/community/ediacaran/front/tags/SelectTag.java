package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class SelectTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/select";
	
	/* ------------ Attr ---------------*/
	
	private Boolean autofocus;
	
	private Boolean readonly;

	private Boolean required;
	
	private Boolean multiple;
	
	private Integer sizeList;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String size;

	private JspFragmentVarParser options;
	
	public SelectTag() {
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.options = new JspFragmentVarParser(getJspBody());
		vars.put("empty",   label == null? "sr-only" : null);
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
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

	public JspFragmentVarParser getOptions() {
		return options;
	}

	public void setOptions(JspFragmentVarParser options) {
		this.options = options;
	}

}
