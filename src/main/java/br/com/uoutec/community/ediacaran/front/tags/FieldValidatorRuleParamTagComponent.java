package br.com.uoutec.community.ediacaran.front.tags;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTagComponent.ValidatorParamEntity;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="field-validator-param", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldValidatorRuleParamTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/content";
	
	private String name;
	
	private String value;
	
	private Boolean raw;
	
	private ByteArrayOutputStream bout;
	
	private PrintWriter contentWriter;
	
	public FieldValidatorRuleParamTagComponent() {
    	this.bout = new ByteArrayOutputStream();
    	this.contentWriter = new PrintWriter(bout, true);
	}
	
	protected void afterBuildComponent(Component component) {
		
    	contentWriter.flush();
    	
    	byte[] bValue = bout.toByteArray();
    	value = new String(bValue);
    	//value = new String(bValue, "utf-8");
    	
    	if(raw == null || !raw) {
	    	value = value.replaceAll("^[\\t\\n\\s]+", "");
	    	value = value.replaceAll("[\\t\\n\\s]+$", "");
	    	value = "\"" + value + "\"";
    	}
    	
    	FieldValidatorRuleTagComponent tag = (FieldValidatorRuleTagComponent)getParentTag();
    	tag.getValidator().getParams().add(new ValidatorParamEntity(name, value));
		
	}
	
    protected Writer getOut() {
    	return contentWriter;
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

	@Override
	public String getType() {
		return "field-validator-param";
	}
	
}
