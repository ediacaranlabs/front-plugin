package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="modal-title", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ModalTitleTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/modal-title";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String type;
	
	public ModalTitleTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getType() {
		return type == null? "h4" : type;
	}

	@TagAttribute
	public void setType(String type) {
		this.type = type;
	}

	
}