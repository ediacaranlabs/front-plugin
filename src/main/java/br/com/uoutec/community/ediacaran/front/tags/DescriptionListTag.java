package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class DescriptionListTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/description-list";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String style;
	
	private JspFragmentVarParser content;
	
	public DescriptionListTag() {
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
    
}
