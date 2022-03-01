package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="price-box", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PriceBoxTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/price-box";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String attractiveness;
	
	public PriceBoxTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getAttractiveness() {
		return attractiveness;
	}

	@TagAttribute
	public void setAttractiveness(String attractiveness) {
		this.attractiveness = attractiveness;
	}
    
}
