package br.com.uoutec.community.ediacaran.front.tags;

public class ImageTag  extends AbstractSimpleComponent {

	private static final String TEMPLATE  = "/components/image";
	
	/* ------------ Attr ---------------*/
	
	private String src;

	/* ------------ Prop ---------------*/
	
	private String style; //fluid, thumbnail, rounded, circle (img-<...> | rounded | rounded-circle)
	
	private String align; //left, right, center (float-<...> | mx-auto d-block)
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}
    
}
