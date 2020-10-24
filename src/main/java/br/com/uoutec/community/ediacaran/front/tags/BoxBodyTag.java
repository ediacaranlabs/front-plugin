package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class BoxBodyTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/box-body";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	public BoxBodyTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
	
}
