package br.com.uoutec.community.ediacaran.front.tags;

import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTagComponent.ValidatorEntity;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="field-validator-rule", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldValidatorRuleTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/content";
	
	private String name;
	
	private String message;
	
	private ValidatorEntity validator;

	private Boolean raw;

	protected void beforeBuildComponent(Component component) {
		FieldValidatorTagComponent tag = (FieldValidatorTagComponent)getParentTag();
		
		if(tag == null) {
			throw new IllegalStateException("field not found");
		}
    	
		String msg = message;
		
    	if(raw == null || !raw) {
    		LocalizationContext bundle = getBundle();
    		
			if(bundle != null && msg != null) {
				
				if(msg.startsWith("#{") && msg.endsWith("}")) {
					msg = msg.substring(2, msg.length() - 1);
					msg = bundle.getResourceBundle().getString(msg);
				}
				
			}
    		
			msg = "\"" + msg + "\"";
    	}
    	
    	validator = new ValidatorEntity(name, msg);
		
	}

	protected void afterBuildComponent(Component component) {
    	FieldValidatorTagComponent tag = (FieldValidatorTagComponent)getParentTag();
    	tag.getValidator().add(validator);
    	
		validator = null;
	}
	
    public String getName() {
		return name;
	}

    @TagAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

    @TagAttribute
	public void setMessage(String message) {
		this.message = message;
	}

	public ValidatorEntity getValidator() {
		return validator;
	}

	public void setValidator(ValidatorEntity validator) {
		this.validator = validator;
	}

	public Boolean getRaw() {
		return raw;
	}

    @TagAttribute
	public void setRaw(Boolean raw) {
		this.raw = raw;
	}
		
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	@Override
	public String getType() {
		return "field-validator-rule";
	}
    
}
