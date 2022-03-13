package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="box-header", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BoxHeaderTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/box-header";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public BoxHeaderTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "box-header";
	}
    
}
