package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="modal-header", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ModalHeaderTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/modal-header";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public ModalHeaderTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
