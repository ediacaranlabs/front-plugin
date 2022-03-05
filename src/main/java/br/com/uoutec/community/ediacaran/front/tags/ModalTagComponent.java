package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="modal", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ModalTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/modal";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean fade;
	
	public ModalTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getFade() {
		return fade;
	}

	@TagAttribute
	public void setFade(Boolean fade) {
		this.fade = fade;
	}
	
}
