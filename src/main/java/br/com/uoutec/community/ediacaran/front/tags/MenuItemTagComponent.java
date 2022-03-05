package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="menu-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuItemTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/menu-item";
	
	public static final String TEMPLATE2  = "/components/menu-bar-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String href;
	
	public MenuItemTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return getProperty(MenuTagComponent.CONTEXT_ID) != null? TEMPLATE : TEMPLATE2;
    }

	public String getHref() {
		return href;
	}

	@TagAttribute
	public void setHref(String href) {
		this.href = href;
	}

}
