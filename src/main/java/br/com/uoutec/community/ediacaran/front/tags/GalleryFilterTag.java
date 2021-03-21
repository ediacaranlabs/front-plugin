package br.com.uoutec.community.ediacaran.front.tags;

public class GalleryFilterTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/gallery-filter";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String code;
	
	private String name;
	
	private Boolean active;
	
	public GalleryFilterTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
