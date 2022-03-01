package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="event", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class EventTag extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/event";
	
	private String type;
	
	private String component;
	
    protected TagComponent createTagComponent() {
    	return new TagComponent() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
    	    	if(component == null) {
    				Object cp = super.getParentTag();
    				if(cp != null) {
    					if(cp instanceof AbstractSimpleComponent) {
    						component = ((AbstractSimpleComponent)cp).getId();
    					}
    					else
    					if(cp instanceof AbstractPanelComponent) {
    						component = ((AbstractPanelComponent)cp).getId();
    					}
    				}
    	    	}
    	    }
    		
    	};
    }
	

    public String getType() {
		return type;
	}

    @TagAttribute(required=true)
	public void setType(String type) {
		this.type = type;
	}

	public String getComponent() {
		return component;
	}

    @TagAttribute
	public void setComponent(String component) {
		this.component = component;
	}

	public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
}
