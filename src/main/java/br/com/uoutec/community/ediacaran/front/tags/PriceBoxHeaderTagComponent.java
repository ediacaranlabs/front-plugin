package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="price-box-header", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PriceBoxHeaderTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/price-box-header";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public PriceBoxHeaderTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "price-box-header";
	}
    
}
