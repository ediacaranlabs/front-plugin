package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="data-table", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DataTableTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/data-table";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String action;
	
	public DataTableTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getAction() {
		return action;
	}

	@TagAttribute(required=true)
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getType() {
		return "data-table";
	}
	
}
