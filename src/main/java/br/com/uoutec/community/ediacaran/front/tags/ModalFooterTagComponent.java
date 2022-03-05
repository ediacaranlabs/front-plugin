package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="modal-footer", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ModalFooterTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/modal-footer";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public ModalFooterTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
