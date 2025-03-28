package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="dropdown-separator", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class DropdownSeparatorTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/dropdown-separator";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public DropdownSeparatorTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	@Override
	public String getType() {
		return "dropdown-separator";
	}
    
}
