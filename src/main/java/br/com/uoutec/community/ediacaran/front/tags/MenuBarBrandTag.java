package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="menu-bar-brand", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuBarBrandTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/menu-bar-brand";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String href;
	
	public MenuBarBrandTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getHref() {
		return href;
	}

	@TagAttribute
	public void setHref(String href) {
		this.href = href;
	}

}
