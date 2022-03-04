package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorEntity;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="field-validator-rule", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldValidatorRuleTag extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/content";
	
	private String name;
	
	private String message;
	
	private ValidatorEntity validator;

	private Boolean raw;
	
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

    protected Component createComponent() {
    	return new Component() {
    		
    		protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	
    			FieldValidatorTag tag = (FieldValidatorTag)super.getParentTag();
    			
    			if(tag == null) {
    				throw new IllegalStateException("field not found");
    			}
    	    	
    	    	if(raw == null || !raw) {
    	    		message = "\"" + message + "\"";
    	    	}
    	    	
    	    	validator = new ValidatorEntity(name, message);
    	    	
    	    }
    	    
    	    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	FieldValidatorTag tag = (FieldValidatorTag)super.getParentTag();
    	    	tag.getValidator().add(validator);
    	    	
    			validator = null;
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
}
