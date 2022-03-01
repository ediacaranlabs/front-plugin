package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="append-field", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class AppendFieldTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/append-field";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public AppendFieldTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
