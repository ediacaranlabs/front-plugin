package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
		name="if", 
		uri="https://www.uoutec.com.br/ediacaran/tags/components", 
		bodycontent=BodyTypes.SCRIPTLESS
	)
public class IfTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/if";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String test;
	
	public IfTagComponent() {
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
		return "if";
	}
	
}
