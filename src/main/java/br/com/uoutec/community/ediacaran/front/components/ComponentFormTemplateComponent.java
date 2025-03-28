package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public abstract class ComponentFormTemplateComponent extends AbstractTemplateComponent {

	public static final String FORM = ComponentFormTemplateComponent.class.getSimpleName() + ":form";
	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/form-group.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("name");
			add("value");
			add("required");
			add("enabled");
			add("readonly");
			add("componentType");
			add("form");
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			
			put("enabled", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return value != null && !(Boolean)value? "disabled" : "";
				}
			});

			put("required", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return value != null && (Boolean)value? "required" : "";
				}
				
			});
			
			put("readonly", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return value != null && (Boolean)value? "readonly" : "";
				}
			});
			
			put("componentType", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return value == null? null : "type";
				}
				
			});
			
		}});

	}
	
}
