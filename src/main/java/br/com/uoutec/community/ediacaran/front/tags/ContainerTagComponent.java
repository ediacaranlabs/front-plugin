package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="container", 
	uri="https://www.uoutec.com.br/ediacaran/tags/designer", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ContainerTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/designer/container";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public ContainerTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "container";
	}
    
}
