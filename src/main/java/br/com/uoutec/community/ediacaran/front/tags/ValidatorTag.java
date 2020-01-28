package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.TemplateListVarParser;
import br.com.uoutec.community.ediacaran.front.TemplateVarParser;

public class ValidatorTag extends AbstractBodyTag {

	private static final long serialVersionUID = 748182107582888257L;

	public static final String TEMPLATE  = "/bootstrap4/components/form-validator";

	public static final String TEMPLATE_RULE  = "/bootstrap4/components/form-rule-validator";
	
	public static final String TEMPLATE_RULE_PARAM  = "/bootstrap4/components/form-rule-validator";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_PROPS) {{
			add("button");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	private List<ValidatorEntity> validator;
	
	public ValidatorTag() {
		this.validator = new ArrayList<ValidatorEntity>();
	}
	
	
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }


    public int doEndTag() throws JspException {
    	return super.doEndTag();
    }
	
	public int doAfterBody() {
		FormTag form = (FormTag) super.getProperty(FormTag.FORM, null);
		ComponentFormTag field = (ComponentFormTag)super.getParentTag();
		
		if(form == null) {
			throw new IllegalStateException("form not found");
		}
		
		if(field == null) {
			throw new IllegalStateException("field not found");
		}
		
		try {
			TemplateListVarParser rules = new TemplateListVarParser(TEMPLATE_RULE);
			
			for(ValidatorEntity ve: this.validator) {
				
				TemplateListVarParser params = new TemplateListVarParser(TEMPLATE_RULE_PARAM);
				
				for(ValidatorParamEntity p: ve.params) {
					params.add(p.getName(), p.getValue());
				}
				
				rules.add( ve.getName(), ve.getMessage(), params);
			}
			
			new TemplateVarParser(TEMPLATE)
				.put("form", form.getId())
				.put("field", field.getName())
				.put("rules", rules)
				.parse(bodyContent.getEnclosingWriter());
		} 
		catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return SKIP_BODY;
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }

    protected Set<String> getEmptyAttributes(){
    	return DEFAULT_EMPTY_ATTRIBUTES;
    }
    
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

    protected Set<String> getDefaultProperties(){
    	return DEFAULT_PROPS;
    }

    protected Map<String, AttributeParser> getPropertyParsers(){
    	return DEFAULT_PROPERTY_PARSERS;
    }
	
	public static class ValidatorEntity {
		
		private String name;
		
		private String message;
		
		private List<ValidatorParamEntity> params;

		public ValidatorEntity(String name, String message) {
			super();
			this.name = name;
			this.message = message;
			this.params = new ArrayList<ValidatorParamEntity>();
		}

		public String getName() {
			return name;
		}

		public String getMessage() {
			return message;
		}

		public List<ValidatorParamEntity> getParams() {
			return params;
		}
		
	}
	
	public static class ValidatorParamEntity {
		
		private String name;
		
		private String value;

		public ValidatorParamEntity(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}
		
	}
}
