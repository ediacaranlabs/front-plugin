package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="prettify", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PrettifyTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/prettify";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean linenums;
	
	public PrettifyTag() {
		super.setEscapeContent(true);
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getLinenums() {
		return linenums;
	}

	@TagAttribute
	public void setLinenums(Boolean linenums) {
		this.linenums = linenums;
	}

}
