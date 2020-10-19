package br.com.uoutec.community.ediacaran.front.tags;

import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.ThemeException;
import br.com.uoutec.community.ediacaran.system.tema.TemplateListVarParser;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class FieldValidatorTag extends AbstractPanelComponent {

	private static final long serialVersionUID = 748182107582888257L;

	public static final String TEMPLATE  = "/bootstrap4/components/field-validator";

	public static final String TEMPLATE_RULE  = "/bootstrap4/components/field-rule-validator";
	
	public static final String TEMPLATE_RULE_PARAM  = "/bootstrap4/components/field-rule-validator-param";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleComponent.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleComponent.DEFAULT_PROPS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	private Set<ValidatorEntity> validator;
	
	private String form;
	
	private String field;
	
	public FieldValidatorTag() {
	}
	
    public Set<ValidatorEntity> getValidator() {
		return validator;
	}
	
    public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void doInitBody() throws JspException {
		validator = new HashSet<ValidatorEntity>();
		
		if(form == null) {
			Object formTag = super.getProperty(FormTag.FORM, null);
			if(formTag != null) {
				if(formTag instanceof FormTag) {
					form = ((FormTag)formTag).getId();
				}
				else {
					throw new JspException("invalid form tag: " + formTag);
				}
			}
		}

		if(field == null) {
			Object componentFormTag = super.getParentTag();
			
			if(componentFormTag != null) {
				if(componentFormTag instanceof ComponentFormTag) {
					field = ((ComponentFormTag)componentFormTag).getName();
				}
				else {
					throw new JspException("invalid field tag: " + componentFormTag);
				}
			}
		}
		
		//form = ((FormTag) super.getProperty(FormTag.FORM, null)).getId();
		//field = ((ComponentFormTag)super.getParentTag()).getName();
		
		if(form == null) {
			throw new IllegalStateException("form not found");
		}
		
		if(field == null) {
			throw new IllegalStateException("field not found");
		}

		super.doInitBody();
    }
    
    public int doAfterBody() throws JspException {
    	try {
    		return super.doAfterBody();
    	}
    	finally {
        	validator = null;
        	form      = null;
        	field     = null;
    	}
    }
    
    protected void applyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws ThemeException {
    	
		TemplateListVarParser rules = new TemplateListVarParser(TEMPLATE_RULE);
		
		for(ValidatorEntity ve: this.validator) {
			
			TemplateListVarParser params = new TemplateListVarParser(TEMPLATE_RULE_PARAM);
			
			for(ValidatorParamEntity p: ve.params) {
				params.add(p.getName(), p.getValue());
			}
			
			rules.add(ve.getName(), ve.getMessage(), params);
		}
		
		new TemplateVarParser(TEMPLATE, getTemaPackage(), getTema())
			.put("form", form)
			.put("field", field)
			.put("rules", rules)
			.parse(out);
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
		
		private Set<ValidatorParamEntity> params;

		public ValidatorEntity(String name, String message) {
			super();
			this.name = name;
			this.message = message;
			this.params = new HashSet<ValidatorParamEntity>();
		}

		public String getName() {
			return name;
		}

		public String getMessage() {
			return message;
		}

		public Set<ValidatorParamEntity> getParams() {
			return params;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ValidatorEntity other = (ValidatorEntity) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ValidatorParamEntity other = (ValidatorParamEntity) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
	}
}
