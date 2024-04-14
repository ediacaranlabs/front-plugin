package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.components.ValidatorEntity;
import br.com.uoutec.community.ediacaran.front.components.ValidatorParamEntity;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="field-validator-rule", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldValidatorRuleTagComponent extends SimpleTagSupport {

	private String name;
	
	private String message;
	
	private ValidatorEntity validator;

	private Boolean raw;

    public void doTag() throws JspException, IOException {
    	
    	FieldValidatorTagComponent fvtc = 
    			(FieldValidatorTagComponent)super.getJspContext()
    			.getAttribute(FieldValidatorTagComponent.class.getName());
    	
    	if(fvtc == null) {
			throw new IllegalStateException("field-validator not found");
    	}
    	
    	this.validator = 
    			new ValidatorEntity(
    					name, 
    					message, 
    					raw == null? false : raw.booleanValue()
				);
    	
    	fvtc.addValidator(this.validator);
    	
    	super.getJspContext().setAttribute(FieldValidatorRuleTagComponent.class.getName(), this);
    	
    	try {
    		getJspBody().invoke(getJspContext().getOut());
    	}
    	finally {
        	super.getJspContext().setAttribute(FieldValidatorRuleTagComponent.class.getName(), null);
    	}
    }
	
    public void addRule(ValidatorParamEntity value) {
    	this.validator.getParams().add(value);
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
		
}
