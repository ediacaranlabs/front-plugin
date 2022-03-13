package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="flexslider", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FlexsliderTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/flexslider";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public FlexsliderTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "flexslider";
	}
    
}
