package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;
import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;

public class FieldValidatorComponent extends Component {

	public static final String TEMPLATE            = "/components/field-validator";

	public static final String RULE_TEMPLATE       = "/components/field-rule-validator";
	
	public static final String RULEPARAM_TEMPLATE  = "/components/field-rule-validator-param";

	public FieldValidatorComponent() {
	}
	
	public String getTemplate() {
		return TEMPLATE;
	}

	@SuppressWarnings("unchecked")
	protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
		
		Set<ValidatorEntity> validators = (Set<ValidatorEntity>)vars.get("validators");
		vars.put("validators", getValidators(validators));
		
		String form = (String) vars.get("form");
		vars.put("form", getForm(form));
		
		String field = (String) vars.get("field");
		vars.put("field", getField(field));
    }
	
	protected String getForm(String form) {
		
		if(form != null) {
			return form;
		}
		
		FormComponent formComponent = (FormComponent)getProperty(FormComponent.FORM);

		if(formComponent == null) {
			throw new IllegalStateException("form not found");
		}
	
		return formComponent.getId();
	}

	protected String getField(String field) {
		
		if(field != null) {
			return field;
		}
		
		FieldComponent fieldComponent = (FieldComponent)getProperty(FieldComponent.FORM);

		if(fieldComponent == null) {
			throw new IllegalStateException("field not found");
		}
	
		return fieldComponent.getId();
	}
	
	protected VarParser getValidators(Set<ValidatorEntity> validators) {

		String packageName = getPackageTheme();
		Theme theme = getTheme();
		
		TemplateListVarParser rules = new TemplateListVarParser(RULE_TEMPLATE, packageName, this, theme);
		
		for(ValidatorEntity ve: validators) {
			
			TemplateListVarParser params = new TemplateListVarParser(RULEPARAM_TEMPLATE, packageName, this, theme);
			
			for(ValidatorParamEntity p: ve.getParams()) {
				params.add(p.getName(), p.getValue());
			}
			
			rules.add(ve.getName(), ve.getMessage(), params);
		}
		
		return rules;
    }
	
}
