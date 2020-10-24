package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class FlexsliderTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/flexslider";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser imgs;
	
	public FlexsliderTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.imgs = new JspFragmentVarParser(getJspBody());
	}
    
	public JspFragmentVarParser getImgs() {
		return imgs;
	}

	public void setImgs(JspFragmentVarParser imgs) {
		this.imgs = imgs;
	}
    
}
