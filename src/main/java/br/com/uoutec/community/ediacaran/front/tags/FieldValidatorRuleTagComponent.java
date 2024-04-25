package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
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

	private LocalizationContext bundle;
	
    public void doTag() throws JspException, IOException {
    	
    	FieldValidatorTagComponent fvtc = 
    			(FieldValidatorTagComponent)super.getJspContext()
    			.getAttribute(FieldValidatorTagComponent.class.getName());
    	
    	if(fvtc == null) {
			throw new IllegalStateException("field-validator not found");
    	}
    	
    	if(message.startsWith("#{") && message.endsWith("}")) {
    		
    	}
    	this.validator = 
    			new ValidatorEntity(
    					name, 
    					getMsg(), 
    					raw == null? false : raw.booleanValue()
				);
    	
    	fvtc.addValidator(this.validator);
    	
    	if(getJspBody() == null) {
    		return;
    	}
    	
    	super.getJspContext().setAttribute(FieldValidatorRuleTagComponent.class.getName(), this);
    	
    	try {
    		getJspBody().invoke(getJspContext().getOut());
    	}
    	finally {
        	super.getJspContext().setAttribute(FieldValidatorRuleTagComponent.class.getName(), null);
    	}
    }
	
    private String getMsg() {
    	if(message.startsWith("#{") && message.endsWith("}")) {
    		
    		if(bundle == null) {
    			throw new NullPointerException("bundle");
    		}
    		
			String value = message.substring(2, message.length() - 1);
			return String.valueOf(bundle.getResourceBundle().getObject(value));
    	}
    	
    	return message;
    }
    
    public void addRule(ValidatorParamEntity value) {
    	this.validator.getParams().add(value);
    }
    
    public LocalizationContext getBundle() {
		return bundle;
	}

    @TagAttribute
	public void setBundle(LocalizationContext bundle) {
		this.bundle = bundle;
	}

	public String getName() {
		return name;
	}

    @TagAttribute(required = true)
	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

    @TagAttribute(required = true)
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
