package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="right", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class RightTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/right";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public RightTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	@Override
	public String getType() {
		return "center";
	}
    
}
