package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="widget", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class WidgetTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/widget";

	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	public WidgetTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getTitle() {
		return title;
	}

	@TagAttribute(required=true)
	public void setTitle(String title) {
		this.title = title;
	}

}
