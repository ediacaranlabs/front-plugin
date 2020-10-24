package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class DropdownItemTag  extends ComponentFormTag {

	public static final String TEMPLATE  = "/components/dropdown-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String src;
	
	private JspFragmentVarParser content;
	
	public DropdownItemTag() {
	}

    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
	
}
