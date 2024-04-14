package br.com.uoutec.community.ediacaran.front.tags;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.components.ValidatorParamEntity;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="field-validator-param", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldValidatorRuleParamTagComponent 
	extends SimpleTagSupport {

	private String name;
	
	private String value;
	
	private Boolean raw;
	
	public FieldValidatorRuleParamTagComponent() {
	}
	
    public void doTag() throws JspException, IOException {
    	
    	FieldValidatorRuleTagComponent parent = 
    			(FieldValidatorRuleTagComponent)super.getJspContext()
    			.getAttribute(FieldValidatorRuleTagComponent.class.getName());
    	
    	if(parent == null) {
			throw new IllegalStateException("field-validator-rule not found");
    	}
    	
    	if(value != null) {
    		parent.addRule(
    				new ValidatorParamEntity(
    						name, 
    						value, 
    						raw == null? false : raw.booleanValue()
					)
			);
    		
    		return;
    	}
    	try(ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
        	
    		PrintWriter writer = new PrintWriter(bout, true);
    		getJspBody().invoke(writer);
    		writer.flush();
    		
        	byte[] bValue = bout.toByteArray();
        	value = new String(bValue);
	    	value = value.replaceAll("^[\\t\\n\\s]+", "");
	    	value = value.replaceAll("[\\t\\n\\s]+$", "");
        	
    		parent.addRule(
    				new ValidatorParamEntity(
    						name, 
    						value, 
    						raw == null? false : raw.booleanValue()
					)
			);
    	}
    	
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

	public Boolean getRaw() {
		return raw;
	}

    @TagAttribute
	public void setRaw(Boolean raw) {
		this.raw = raw;
	}
	
}
