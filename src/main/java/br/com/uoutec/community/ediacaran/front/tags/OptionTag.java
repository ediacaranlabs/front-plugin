package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class OptionTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/option";
	
	/* ------------ Attr ---------------*/
	
	private Boolean selected;
	
	private String label;
	
	/* ------------ Prop ---------------*/

	private JspFragmentVarParser content;
	
	public OptionTag() {
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

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
