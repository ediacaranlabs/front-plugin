package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

/*
@Tag(
	name="intro", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
*/
public class IntroTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/intro";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String bgImage;
	
	public IntroTagComponent() {
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

	@Override
	public String getType() {
		return "intro";
	}
	
}
