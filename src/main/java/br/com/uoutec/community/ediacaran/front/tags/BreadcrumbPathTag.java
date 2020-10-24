package br.com.uoutec.community.ediacaran.front.tags;

public class BreadcrumbPathTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/breadcrumb-path";
	
	public static final String TEMPLATE_ICON = "/components/icon";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String icon;
	
	private String text;
	
	private String lnk;
	
	public BreadcrumbPathTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLnk() {
		return lnk;
	}

	public void setLnk(String lnk) {
		this.lnk = lnk;
	}
    
}
