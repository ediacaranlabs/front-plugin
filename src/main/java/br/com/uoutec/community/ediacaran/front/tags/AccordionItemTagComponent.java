package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="accordion-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class AccordionItemTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/accordion-item";

	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	/* ------------ Private Prop ---------------*/
	
	private String parentID;
	
	public AccordionItemTagComponent() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
    	    	Object parentTag = getParentTag();
    			parentID = parentTag == null? null : ((AccordionTagComponent)parentTag).getId();
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	public String getTitle() {
		return title;
	}

	@TagAttribute
	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentID() {
		return parentID == null? ((AccordionTagComponent)getParentTag()).getId() : parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}	
    
}
