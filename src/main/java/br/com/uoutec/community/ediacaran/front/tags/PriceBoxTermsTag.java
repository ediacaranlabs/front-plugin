package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="price-box-terms", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PriceBoxTermsTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/price-box-terms";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public PriceBoxTermsTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
