package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
		name="price-box-footer", 
		uri="https://www.uoutec.com.br/ediacaran/tags/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class PriceBoxFooterTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/price-box-footer";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public PriceBoxFooterTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "price-box-footer";
	}
    
}
