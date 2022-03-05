package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="dropdown-separator", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.EMPTY
)
public class DropdownSeparatorTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/dropdown-separator";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public DropdownSeparatorTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
}
