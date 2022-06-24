package br.com.uoutec.community.ediacaran.front.page;

public class BreadcrumbPath {

	private String name;
	
	private String icon;
	
	private String link;

	public BreadcrumbPath() {
		this(null, null, null);
	}
	
	public BreadcrumbPath(String name, String icon, String link) {
		this.name = name;
		this.icon = icon;
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
