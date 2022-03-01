package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="description-list", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DescriptionListTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/description-list";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public DescriptionListTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
