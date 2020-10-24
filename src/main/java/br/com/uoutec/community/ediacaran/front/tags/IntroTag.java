package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class IntroTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/intro";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String bgImage;
	
	private JspFragmentVarParser content;
	
	public IntroTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
    
}
