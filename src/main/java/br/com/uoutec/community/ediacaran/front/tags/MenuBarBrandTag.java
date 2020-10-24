package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class MenuBarBrandTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/menu-bar-brand";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String href;
	
	private JspFragmentVarParser content;
	
	public MenuBarBrandTag() {
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
