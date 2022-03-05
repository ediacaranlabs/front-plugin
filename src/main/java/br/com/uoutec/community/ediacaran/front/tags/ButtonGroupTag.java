package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="button-group", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ButtonGroupTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/button-group";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public ButtonGroupTag() {
	}

    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
