package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="menu-itens", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuItensTag  extends AbstractSimpleComponent {

	public static final String WRAPPER_TEMPLATE = "/components/menu-collapse";
	
	public static final String TEMPLATE  		= "/components/menu-itens";
	
	public static final String TEMPLATE_2  		= "/components/menu-itens-menu";
	
	public static final String TEMPLATE_CONTENT	= "/components/menu-itens-menu-content";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	private MenuTag menu;
	
	private String align; //left, right, center
	
	private String menuAlign; //left, right
	
	private Boolean collapse;
	
	private String resource;
	
	public MenuItensTag() {
	}
	
	protected void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
    	if(menu != null) {
    		vars.put("parent-id", menu.getId());
    	}
    }
	
    protected String getDefaultTemplate() {
		menu = (MenuTag)getProperty(MenuTag.CONTEXT_ID);
		
		if(menu == null) {
			if(Boolean.TRUE.equals(collapse)) {
				super.setWrapper(true);
			}
		}
		
    	return menu != null? 
    			(resource != null? TEMPLATE_CONTENT : TEMPLATE_2) : 
				TEMPLATE;
    }

    protected String getWrapperTemplate() {
    	return WRAPPER_TEMPLATE;
    }
    
	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getAlign() {
		return align;
	}

	@TagAttribute
	public void setAlign(String align) {
		this.align = align;
	}

	public MenuTag getMenu() {
		return menu;
	}

	public void setMenu(MenuTag menu) {
		this.menu = menu;
	}

	public String getMenuAlign() {
		return menuAlign;
	}

	@TagAttribute
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

}
