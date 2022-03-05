package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="menu-bar", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuBarTagComponent  extends AbstractSimpleTagComponent {

	public static final String CONTEXT_ID = MenuBarTagComponent.class.getName() + ":CONTEXT";
	
	public static final String TEMPLATE  = "/components/menu-bar";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean sidebar;
	
	//private String style; //light, dark
	
	private String background; //dark, primary
	
	private String position; //top, bottom, sticky
	
	private String expand; //sm, md, lg, xl
	
	public MenuBarTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getBackground() {
		return background;
	}

	@TagAttribute
	public void setBackground(String background) {
		this.background = background;
	}

	public String getPosition() {
		return position;
	}

	@TagAttribute
	public void setPosition(String position) {
		this.position = position;
	}

	public String getExpand() {
		return expand;
	}

	@TagAttribute
	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Boolean getSidebar() {
		return sidebar;
	}

	@TagAttribute
	public void setSidebar(Boolean sidebar) {
		this.sidebar = sidebar;
	}

}
