package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="image", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ImageTag  extends AbstractSimpleComponent {

	private static final String TEMPLATE  = "/components/image";
	
	/* ------------ Attr ---------------*/
	
	private String src;

	/* ------------ Prop ---------------*/
	
	//private String style; //fluid, thumbnail, rounded, circle (img-<...> | rounded | rounded-circle)
	
	//private String align; //left, right, center (float-<...> | mx-auto d-block)
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getSrc() {
		return src;
	}

	@TagAttribute(required=true)
	public void setSrc(String src) {
		this.src = src;
	}
	
}
