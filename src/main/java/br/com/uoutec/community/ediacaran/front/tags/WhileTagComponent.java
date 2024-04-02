package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="while", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class WhileTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/while";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String test;
	
	public WhileTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getTest() {
		return test;
	}

	@TagAttribute(required=true)
	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public String getType() {
		return "while";
	}
	
}
