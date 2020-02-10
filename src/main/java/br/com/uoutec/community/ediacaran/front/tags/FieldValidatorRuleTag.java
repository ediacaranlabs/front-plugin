package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorEntity;

public class FieldValidatorRuleTag extends AbstractBodyTag {

	private static final long serialVersionUID = 748182107582888257L;

	public static final String TEMPLATE  = "";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractBodyTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractBodyTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractBodyTag.DEFAULT_PROPS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractBodyTag.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	private String name;
	
	private String message;
	
	private ValidatorEntity validator;

	private Boolean raw;
	
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

	public void doInitBody() throws JspException {
		
		FieldValidatorTag tag = (FieldValidatorTag)super.getParentTag();
		
		if(tag == null) {
			throw new IllegalStateException("field not found");
		}
    	
    	if(raw == null || !raw) {
    		message = "\"" + message + "\"";
    	}
    	
    	this.validator = new ValidatorEntity(name, message);
		
		super.doInitBody();
    }
	
    public int doAfterBody() throws JspException {
    	super.doAfterBody();
    	FieldValidatorTag tag = (FieldValidatorTag)super.getParentTag();
    	tag.getValidator().add(validator);
    	
		this.validator = null;
		
    	return SKIP_BODY;
    }
	
    protected String getDefaultTemplate() {
    	return null;
    }
    
}
