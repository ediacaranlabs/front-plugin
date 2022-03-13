package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="treeview-itens", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TreeviewItensTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/treeview-itens";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TreeviewItensTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "treeview-itens";
	}
    
}
