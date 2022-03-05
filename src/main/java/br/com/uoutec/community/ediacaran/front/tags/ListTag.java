package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
		name="list", 
		uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class ListTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE   = "/components/list";
	
	public static final String TEMPLATE2  = "/components/ordered-list";
	
	public static final String PARENT = ListTag.class.getSimpleName() + ":PARENT";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	//private String style; //inline, unstyled, ordered
	
	private Object oldParent;
	
	public ListTag() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    			super.beforeApplyTemplate(template, vars, out);
    	    	oldParent = setProperty(PARENT, ListTag.this);
    		}

    	    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	super.afterApplyTemplate(template, vars, out);
    	    	super.setProperty(PARENT, oldParent);
    	    	oldParent = null;
    	    }
    		
    	};
    }

    public String getDefaultTemplate() {
    	return "ordered".equals(getStyle())? TEMPLATE2 : TEMPLATE;
    }

}
