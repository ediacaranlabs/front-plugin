package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class PriceBoxTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/price-box";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	private String attractiveness;
	
	public PriceBoxTag() {
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

	public String getAttractiveness() {
		return attractiveness;
	}

	public void setAttractiveness(String attractiveness) {
		this.attractiveness = attractiveness;
	}
    
}
