package br.com.uoutec.community.ediacaran.front.tags;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorParamEntity;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="field-validator-param", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldValidatorRuleParamTag extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/content";
	
	private String name;
	
	private String value;
	
	private Boolean raw;
	
	private ByteArrayOutputStream bout;
	
	private PrintWriter contentWriter;
	
	public FieldValidatorRuleParamTag() {
    	this.bout = new ByteArrayOutputStream();
    	this.contentWriter = new PrintWriter(bout, true);
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    	    public Writer getOut() {
    	    	return contentWriter;
    	    }

    	    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	
    	    	contentWriter.flush();
    	    	
    	    	byte[] bValue = bout.toByteArray();
    	    	value = new String(bValue, "utf-8");
    	    	
    	    	if(raw == null || !raw) {
    		    	value = value.replaceAll("^[\\t\\n\\s]+", "");
    		    	value = value.replaceAll("[\\t\\n\\s]+$", "");
    		    	value = "\"" + value + "\"";
    	    	}
    	    	
    	    	FieldValidatorRuleTag tag = (FieldValidatorRuleTag)super.getParentTag();
    	    	tag.getValidator().getParams().add(new ValidatorParamEntity(name, value));
    	    	
    	    }
    	    
    	};
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

	public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
