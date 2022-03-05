package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="treeview", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TreeViewTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/treeview";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TreeViewTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
