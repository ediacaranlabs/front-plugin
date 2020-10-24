package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class RadioTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/radio";
	
	/* ------------ Attr ---------------*/
	
	private Boolean selected;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private Boolean inline;
	
	private JspFragmentVarParser content;
	
	public RadioTag() {
		setComponentType("radio");
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
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

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
