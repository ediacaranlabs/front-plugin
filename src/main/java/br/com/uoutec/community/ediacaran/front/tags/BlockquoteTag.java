package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class BlockquoteTag extends AbstractSimpleComponent {

	public static final String TEMPLATE      = "/components/blockquote";
	
	public static final String CITE_TEMPLATE = "/components/cite";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String cite;
	
	private JspFragmentVarParser content;
	
	public BlockquoteTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
	
	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
	
}
