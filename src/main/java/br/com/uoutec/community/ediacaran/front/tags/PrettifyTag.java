package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class PrettifyTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/prettify";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean linenums;
	
	private JspFragmentVarParser content;
	
	public PrettifyTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public Boolean getLinenums() {
		return linenums;
	}

	public void setLinenums(Boolean linenums) {
		this.linenums = linenums;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
    
}
