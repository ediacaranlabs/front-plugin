package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="menu-itens", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuItensTagComponent  extends AbstractSimpleTagComponent {

	public static final String WRAPPER_TEMPLATE = "/components/menu-collapse";
	
	public static final String TEMPLATE  		= "/components/menu-itens";
	
	public static final String TEMPLATE_2  		= "/components/menu-itens-menu";
	
	public static final String TEMPLATE_CONTENT	= "/components/menu-itens-menu-content";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private MenuTagComponent menu;
	
	//private String align; //left, right, center
	
	@Deprecated
	private String menuAlign; //left, right
	
	private Boolean collapse;
	
	private String resource;
	
	public MenuItensTagComponent() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		protected void beforePrepareVars(Map<String, Object> vars) {
    	    	if(menu != null) {
    	    		vars.put("parent-id", menu.getId());
    	    	}
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
		menu = (MenuTagComponent)getProperty(MenuTagComponent.CONTEXT_ID);
		
		if(menu == null) {
			if(Boolean.TRUE.equals(collapse)) {
				super.setWrapper(true);
			}
		}
		
    	return menu != null? 
    			(resource != null? TEMPLATE_CONTENT : TEMPLATE_2) : 
				TEMPLATE;
    }

    public String getWrapperTemplate() {
    	return WRAPPER_TEMPLATE;
    }
    
	public MenuTagComponent getMenu() {
		return menu;
	}

	public void setMenu(MenuTagComponent menu) {
		this.menu = menu;
	}

	public String getMenuAlign() {
		return menuAlign;
	}

	//@TagAttribute
	public void setMenuAlign(String menuAlign) {
		this.menuAlign = menuAlign;
	}

	public Boolean getCollapse() {
		return collapse;
	}

	@TagAttribute
	public void setCollapse(Boolean collapse) {
		this.collapse = collapse;
	}

	public String getResource() {
		return resource;
	}

	@TagAttribute
	public void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public String getType() {
		return "menu-itens";
	}
	
}
