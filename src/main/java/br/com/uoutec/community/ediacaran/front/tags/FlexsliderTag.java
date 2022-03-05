package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="flexslider", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FlexsliderTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/flexslider";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public FlexsliderTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
