package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="table-footer", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TableFooterTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/table-foot";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TableFooterTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
