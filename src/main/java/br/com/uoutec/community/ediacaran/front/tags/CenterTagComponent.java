package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="center", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class CenterTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/center";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public CenterTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	@Override
	public String getType() {
		return "center";
	}
    
}
