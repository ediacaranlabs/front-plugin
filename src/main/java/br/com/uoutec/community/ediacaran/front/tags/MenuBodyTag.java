package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="menu-body", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuBodyTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/menu-body";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String menuID;

	public MenuBodyTag() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    	    protected void beforeApplyTemplate(String template, 
    	    		Map<String,Object> vars, Writer out) throws IOException {
    	    	Object id = menuID == null? ((MenuBarTag)getProperty(MenuBarTag.CONTEXT_ID)).getId() : menuID;
    	    	
    	    	if(id == null) {
    	    		throw new RuntimeException("menu id not found");
    	    	}
    	    	
    	    	vars.put("menu-id", id);
    	    }
    		
    	};
    }
	
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getMenuID() {
		return menuID;
	}

	@TagAttribute
	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

}
