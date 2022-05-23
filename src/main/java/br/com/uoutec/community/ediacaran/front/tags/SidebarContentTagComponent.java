package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="sidebar-content", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class SidebarContentTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/sidebar-content";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public SidebarContentTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "sidebar-content";
	}

}
