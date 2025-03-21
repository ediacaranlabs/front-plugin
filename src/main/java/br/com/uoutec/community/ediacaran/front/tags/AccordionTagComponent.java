package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="accordion", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class AccordionTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/accordion";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	/* ------------ Private Prop ---------------*/
	
	public AccordionTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "accordion";
	}
    
}
