package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="breadcrumb", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BreadcrumbTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/breadcrumb";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	public BreadcrumbTagComponent() {
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

	@Override
	public String getType() {
		return "breadcrumb";
	}
	
}
