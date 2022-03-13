package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="box-body", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BoxBodyTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/box-body";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public BoxBodyTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "box-body";
	}
    
}
