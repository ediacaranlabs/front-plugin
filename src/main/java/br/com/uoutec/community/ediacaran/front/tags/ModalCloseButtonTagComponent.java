package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="modal-close-button", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ModalCloseButtonTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/modal-close-button";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	public ModalCloseButtonTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getLabel() {
		return label == null? "×" : label;
	}

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	
}
