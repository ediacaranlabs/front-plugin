package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="autocomplete", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class AutocompleteTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/autocomplete";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String fieldID;
	
	private String resource;
	
	public AutocompleteTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	protected void beforeBuildComponent(Component component) {
    	if(fieldID == null) {
    		
			Object cp = getParentTag();
			
			if(cp != null) {
				
				if(cp instanceof AbstractSimpleTagComponent) {
					fieldID = ((AbstractSimpleTagComponent)cp).getId();
				}
				else
				if(cp instanceof AbstractBodyTagComponent) {
					fieldID = ((AbstractBodyTagComponent)cp).getId();
				}
				
			}
			
    	}
	}
    
	public String getFieldID() {
		return fieldID;
	}

	@TagAttribute
	public void setFieldID(String fieldID) {
		this.fieldID = fieldID;
	}

	public String getResource() {
		return resource;
	}

	@TagAttribute(required=true)
	public void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public String getType() {
		return "autocomplete";
	}
	
}
