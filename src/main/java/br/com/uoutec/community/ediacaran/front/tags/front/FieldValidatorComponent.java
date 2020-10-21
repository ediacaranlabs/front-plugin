package br.com.uoutec.community.ediacaran.front.tags.front;

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

public class FieldValidatorComponent extends AbstractPanelComponent {

	private static final long serialVersionUID = 748182107582888257L;

	
	protected void loadConfiguration() {

		TEMPLATE  = "/bootstrap4/components/field-validator";

	
	protected void loadConfiguration() {

		TEMPLATE_RULE  = "/bootstrap4/components/field-rule-validator";
	
	
	protected void loadConfiguration() {

		TEMPLATE_RULE_PARAM  = "/bootstrap4/components/field-rule-validator-param";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	}
}
