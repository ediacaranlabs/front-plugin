package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="separator", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.EMPTY
)
public class SeparatorTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/separator";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public SeparatorTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
