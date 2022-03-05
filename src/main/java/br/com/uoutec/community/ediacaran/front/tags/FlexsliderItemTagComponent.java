package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="flexslider-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FlexsliderItemTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/flexslider-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String src;
	
	public FlexsliderItemTagComponent() {
	}
	
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
