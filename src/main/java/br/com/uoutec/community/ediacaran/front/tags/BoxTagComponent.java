package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="box", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BoxTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/box";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public BoxTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "box";
	}
    
}
