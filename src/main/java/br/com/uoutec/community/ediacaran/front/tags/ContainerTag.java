package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="container", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ContainerTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/designer/container";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public ContainerTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
