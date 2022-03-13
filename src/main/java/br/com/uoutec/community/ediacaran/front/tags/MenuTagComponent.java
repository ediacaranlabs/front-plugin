package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="menu", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuTagComponent  extends AbstractSimpleTagComponent {

	public static final String CONTEXT_ID 			= MenuTagComponent.class.getName() + ":CONTEXT";

	public static final String TEMPLATE  			= "/components/menu";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public MenuTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "menu";
	}
    
}
