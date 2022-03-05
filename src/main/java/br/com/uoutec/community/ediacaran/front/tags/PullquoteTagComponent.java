package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="pullquote", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PullquoteTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/pullquote";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String align; //(ex: left ou right)
	
	public PullquoteTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getAlign() {
		return align;
	}

	@TagAttribute
	public void setAlign(String align) {
		this.align = align;
	}

}
