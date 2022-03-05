package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="table-body", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TableBodyTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/table-body";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TableBodyTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
