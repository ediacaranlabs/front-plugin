package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="intro", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class IntroTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/intro";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String bgImage;
	
	public IntroTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getBgImage() {
		return bgImage;
	}

	@TagAttribute(required=true)
	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

}
