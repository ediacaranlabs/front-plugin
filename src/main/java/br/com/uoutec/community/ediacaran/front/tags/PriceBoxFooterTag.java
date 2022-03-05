package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
		name="price-box-footer", 
		uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class PriceBoxFooterTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/price-box-footer";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public PriceBoxFooterTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
