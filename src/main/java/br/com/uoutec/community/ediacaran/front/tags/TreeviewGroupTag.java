package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="treeview-group", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TreeviewGroupTag  extends AbstractSimpleComponent {

	public static final String CONTEXT_ID = TreeviewGroupTag.class.getName() + ":CONTEXT";

	public static final String TEMPLATE  = "/components/treeview-group";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TreeviewGroupTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
