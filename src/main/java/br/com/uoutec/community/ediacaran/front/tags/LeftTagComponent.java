package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="left", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class LeftTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/left";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public LeftTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	@Override
	public String getType() {
		return "center";
	}
    
}
