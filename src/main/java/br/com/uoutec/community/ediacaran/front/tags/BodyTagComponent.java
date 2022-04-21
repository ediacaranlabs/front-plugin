package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="body", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BodyTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/body";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	

	public BodyTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getType() {
		return "body";
	}
}
