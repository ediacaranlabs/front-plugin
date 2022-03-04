package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="menu-label", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class MenuLabelTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/menu-label";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public MenuLabelTag() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	vars.put("parent-id", ((MenuTag)getParentTag()).getId());
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
