package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="menu", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuTag  extends AbstractSimpleComponent {

	public static final String CONTEXT_ID 			= MenuTag.class.getName() + ":CONTEXT";

	public static final String TEMPLATE  			= "/components/menu";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public MenuTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
