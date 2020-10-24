package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorEntity;

public class FieldValidatorRuleTag extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/content";
	
	private String name;
	
	private String message;
	
	private ValidatorEntity validator;

	private Boolean raw;
	
	private JspFragmentVarParser content;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

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

	public void setRaw(Boolean raw) {
		this.raw = raw;
	}

    public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	
		FieldValidatorTag tag = (FieldValidatorTag)super.getParentTag();
		
		if(tag == null) {
			throw new IllegalStateException("field not found");
		}
    	
    	if(raw == null || !raw) {
    		message = "\"" + message + "\"";
    	}
    	
    	this.validator = new ValidatorEntity(name, message);
    	
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	FieldValidatorTag tag = (FieldValidatorTag)super.getParentTag();
    	tag.getValidator().add(validator);
    	
		this.validator = null;
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
}
