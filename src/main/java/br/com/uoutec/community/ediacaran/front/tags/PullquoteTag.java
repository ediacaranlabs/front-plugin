package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class PullquoteTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/pullquote";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String align;
	
	private JspFragmentVarParser content;
	
	public PullquoteTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
