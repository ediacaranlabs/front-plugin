package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;;

@Tag(
	name="carousel-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class CarouselItemTag extends AbstractSimpleTagComponent {

	public static final String TEMPLATE   = "/components/carousel-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
