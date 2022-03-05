package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="content-section", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ContentSectionTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/content-section";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	public ContentSectionTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getTitle() {
		return title;
	}

	@TagAttribute
	public void setTitle(String title) {
		this.title = title;
	}

}
