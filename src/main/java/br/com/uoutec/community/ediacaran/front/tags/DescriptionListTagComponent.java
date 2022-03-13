package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="description-list", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DescriptionListTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/description-list";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public DescriptionListTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "description-list";
	}
    
}
