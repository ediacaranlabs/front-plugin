package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="prepend-field", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PrependFieldTag extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/prepend-field";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public PrependFieldTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
