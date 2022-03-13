package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="menu-label", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuLabelTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/menu-label";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public MenuLabelTagComponent() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	vars.put("parent-id", ((MenuTagComponent)getParentTag()).getId());
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	@Override
	public String getType() {
		return "menu-label";
	}
    
}
