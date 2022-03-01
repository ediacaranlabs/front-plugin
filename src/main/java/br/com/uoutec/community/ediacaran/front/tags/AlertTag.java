package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="alert", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class AlertTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/alert";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public AlertTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
}
