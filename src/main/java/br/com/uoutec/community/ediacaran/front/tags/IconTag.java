package br.com.uoutec.community.ediacaran.front.tags;

public class IconTag extends AbstractSimpleComponent {

	public static final String TEMPLATE       = "/components/icon";
	
	public static final String TEMPLATE_STACK = "/components/icon-stack";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer size;
	
	private Integer bgSize;
	
	private Integer iconSize;
	
	private String bg;
	
	private String icon;
	
	public IconTag() {
	}

    protected String getDefaultTemplate() {
    	return bg != null? TEMPLATE_STACK : TEMPLATE;
    }
    
	public Integer getSize() {
		return size == null? 3 : size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getBgSize() {
		return bgSize == null? getSize() - 1 : bgSize;
	}

	public void setBgSize(Integer bgSize) {
		this.bgSize = bgSize;
	}

	public Integer getIconSize() {
		return iconSize == null? getBgSize() - 1 : iconSize;
	}

	public void setIconSize(Integer iconSize) {
		this.iconSize = iconSize;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
