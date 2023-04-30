package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="event", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class EventTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/event";
	
	private String type;
	
	private String componentName;
	
	protected void beforeBuildComponent(Component component) {
    	if(componentName == null) {
    		
			Object cp = getParentTag();
			
			if(cp != null) {
				
				if(cp instanceof AbstractSimpleTagComponent) {
					componentName = ((AbstractSimpleTagComponent)cp).getId();
				}
				else
				if(cp instanceof AbstractBodyTagComponent) {
					componentName = ((AbstractBodyTagComponent)cp).getId();
				}
				
			}
			
    	}
	}

    public String getType() {
		return type;
	}

    @TagAttribute(required=true)
	public void setType(String type) {
		this.type = type;
	}

	public String getComponentName() {
		return componentName;
	}

    @TagAttribute(displayName="component")
	public void setComponentName(String value) {
		this.componentName = value;
	}

	public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
}
