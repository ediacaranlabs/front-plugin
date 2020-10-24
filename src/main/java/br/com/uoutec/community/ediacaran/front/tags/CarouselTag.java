package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class CarouselTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/carousel";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	public CarouselTag() {
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
    
}
