package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="carousel", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class CarouselTag extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/carousel";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public CarouselTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
