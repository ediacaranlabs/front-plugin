package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="price-box", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PriceBoxTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/price-box";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	//private String style; //special
	
	public PriceBoxTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "price-box";
	}
	
}
