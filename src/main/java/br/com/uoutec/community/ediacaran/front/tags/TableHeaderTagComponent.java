package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="table-header", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TableHeaderTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/table-header";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TableHeaderTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "table-header";
	}
    
}
