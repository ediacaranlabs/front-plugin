package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="alert", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class AlertTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/alert";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public AlertTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	@Override
	public String getType() {
		return "alert";
	}
    
}
