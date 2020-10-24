package br.com.uoutec.community.ediacaran.front.tags;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.FieldValidatorTag.ValidatorParamEntity;

public class FieldValidatorRuleParamTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/content";
	
	private String name;
	
	private String value;
	
	private Boolean raw;
	
	private JspFragmentVarParser content;

	private ByteArrayOutputStream bout;
	
	private PrintWriter contentWriter;
	
	public FieldValidatorRuleParamTag() {
    	this.bout = new ByteArrayOutputStream();
    	this.contentWriter = new PrintWriter(bout, true);
	}
	
    protected Writer getOut() {
    	return contentWriter;
    }
	
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	
    	this.contentWriter.flush();
    	
    	byte[] bValue = this.bout.toByteArray();
    	this.value = new String(bValue, "utf-8");
    	
    	if(raw == null || !raw) {
	    	this.value = value.replaceAll("^[\\t\\n\\s]+", "");
	    	this.value = value.replaceAll("[\\t\\n\\s]+$", "");
	    	this.value = "\"" + value + "\"";
    	}
    	
    	FieldValidatorRuleTag tag = (FieldValidatorRuleTag)super.getParentTag();
    	tag.getValidator().getParams().add(new ValidatorParamEntity(this.name, this.value));
    	
    }
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public Boolean getRaw() {
		return raw;
	}

	public void setRaw(Boolean raw) {
		this.raw = raw;
	}

	protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
	
}
