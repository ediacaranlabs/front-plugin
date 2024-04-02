package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="forEach", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ForEachTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/foreach";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String items;
	
	private String var;
	
	public ForEachTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	public String getItems() {
		return items;
	}

	@TagAttribute(required=true)
	public void setItems(String items) {
		this.items = items;
	}

	public String getVar() {
		return var;
	}

	@TagAttribute(required=true)
	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public String getType() {
		return "foreach";
	}
	
}
