package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="gallery-filter", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.EMPTY
)
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

	@TagAttribute(required=true)
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	@TagAttribute(required=true)
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	@TagAttribute
	public void setActive(Boolean active) {
		this.active = active;
	}

}
