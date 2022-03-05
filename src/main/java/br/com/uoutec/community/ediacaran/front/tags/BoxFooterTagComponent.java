package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
		name="box-footer", 
		uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class BoxFooterTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/box-footer";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public BoxFooterTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
