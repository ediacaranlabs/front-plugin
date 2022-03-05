package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="property-config-list", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PropertyConfigListTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE 			= "/components/property-config-list";

	public static final String TEMPLATE_NAME 		= "/components/property-config-list-name";
	
	public static final String TEMPLATE_VALUE 		= "/components/property-config-list-value";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String parentID;
	
	private String name;
	
	public PropertyConfigListTagComponent() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
    			Object cp = super.getParentTag();
    			if(cp != null) {
    				if(cp instanceof AbstractSimpleTagComponent) {
    					parentID = ((AbstractSimpleTagComponent)cp).getId();
    				}
    				else
    				if(cp instanceof AbstractBodyTagComponent) {
    					parentID = ((AbstractBodyTagComponent)cp).getId();
    				}
    			}
    			
    		}
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	
    	if(!(super.getParentTag() instanceof PropertyConfigTagComponent || super.getParentTag() instanceof PropertyConfigListTagComponent)) {
    		return TEMPLATE;
    	}

    	if(super.getParentTag() instanceof PropertyConfigListTagComponent) {
    		return TEMPLATE_VALUE;
    	}

		return TEMPLATE_NAME;
    }

	public String getName() {
		return name;
	}

	@TagAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

}
