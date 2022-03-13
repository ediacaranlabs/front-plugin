package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="treeview", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TreeViewTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/treeview";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TreeViewTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "treeview";
	}
    
}
