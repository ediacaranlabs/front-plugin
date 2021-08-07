package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="property-config", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class PropertyConfigTag extends AbstractSimpleComponent {

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
	
	private JspFragmentVarParser content;
	
	public PropertyConfigTag() {
	}
	
    protected String getDefaultTemplate() {
    	
    	if(!(super.getParentTag() instanceof PropertyConfigTag || super.getParentTag() instanceof PropertyConfigListTag)) {
    		return TEMPLATE;
    	}

    	if(super.getParentTag() instanceof PropertyConfigListTag) {
    		return TEMPLATE_VALUE;
    	}

    	if(name != null) {
    		return value != null? TEMPLATE_NAME_VALUE : TEMPLATE_NAME;
    	}

		return TEMPLATE_VALUE;
    }

	public void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
		this.content = new JspFragmentVarParser(getJspBody());
		
		Object cp = super.getParentTag();
		if(cp != null) {
			if(cp instanceof AbstractSimpleComponent) {
				parentID = ((AbstractSimpleComponent)cp).getId();
			}
			else
			if(cp instanceof AbstractPanelComponent) {
				parentID = ((AbstractPanelComponent)cp).getId();
			}
		}
		
	}
    
	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
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
	
}
