package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="sidebar-group", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class SidebarGroupTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/sidebar-group";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean show;
	
	public SidebarGroupTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getType() {
		return "sidebar-group";
	}

	public Boolean getShow() {
		return show;
	}

	@TagAttribute
	public void setShow(Boolean show) {
		this.show = show;
	}

}
