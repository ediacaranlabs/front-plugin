package br.com.uoutec.community.ediacaran.front.tags;

public class FlexsliderItemTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/flexslider-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String src;
	
	public FlexsliderItemTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
}
