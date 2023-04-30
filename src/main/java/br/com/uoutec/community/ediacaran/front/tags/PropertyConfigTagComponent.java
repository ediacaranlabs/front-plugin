package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="property-config", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PropertyConfigTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE 			= "/components/property-config";

	public static final String TEMPLATE_NAME_VALUE 	= "/components/property-config-name-value";
	
	public static final String TEMPLATE_NAME 		= "/components/property-config-name";
	
	public static final String TEMPLATE_VALUE		= "/components/property-config-value";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String parentID;
	
	private String name;
	
	private String value;
	
	private Boolean raw;
	
	public PropertyConfigTagComponent() {
	}
	
	protected void beforeBuildComponent(Component component) {
		Object cp = getParentTag();
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
	
    public String getDefaultTemplate() {
    	
    	if(!(super.getParentTag() instanceof PropertyConfigTagComponent || super.getParentTag() instanceof PropertyConfigListTagComponent)) {
    		return TEMPLATE;
    	}

    	if(super.getParentTag() instanceof PropertyConfigListTagComponent) {
    		return TEMPLATE_VALUE;
    	}

    	if(name != null) {
    		return value != null? TEMPLATE_NAME_VALUE : TEMPLATE_NAME;
    	}

		return TEMPLATE_VALUE;
    }

	public String getName() {
		return name;
	}

	@TagAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	@TagAttribute
	public void setValue(String value) {
		this.value = value;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public Boolean getRaw() {
		return raw;
	}

	@TagAttribute
	public void setRaw(Boolean raw) {
		this.raw = raw;
	}
	
	@Override
	public String getType() {
		return "property-config";
	}
	
}
