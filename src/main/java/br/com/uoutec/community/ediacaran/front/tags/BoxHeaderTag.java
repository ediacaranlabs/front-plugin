package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="box-header", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BoxHeaderTag extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/box-header";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public BoxHeaderTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
