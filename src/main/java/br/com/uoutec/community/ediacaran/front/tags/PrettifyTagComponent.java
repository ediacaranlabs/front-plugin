package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="prettify", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PrettifyTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/prettify";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean linenums;
	
	public PrettifyTagComponent() {
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

	@Override
	public String getType() {
		return "prettify";
	}
	
}
