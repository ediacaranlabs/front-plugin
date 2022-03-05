package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="prepend-field-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldGroupItemTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/field-group-item";
	
	public static final String TEMPLATE2  = "/components/content";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean text;
	
	public FieldGroupItemTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return text == null || text? TEMPLATE : TEMPLATE2;
    }

	public Boolean getText() {
		return text;
	}

	@TagAttribute
	public void setText(Boolean text) {
		this.text = text;
	}

}
