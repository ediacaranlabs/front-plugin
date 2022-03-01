package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ComponentProperties;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParserImp;

public abstract class ComponentFormComponent extends AbstractComponent {

	public static final String FORM = ComponentFormComponent.class.getSimpleName() + ":form";
	
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
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			
			put("enabled", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, ComponentProperties component) {
					return value != null && !(Boolean)value? "disabled" : "";
				}
			});

			put("required", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, ComponentProperties component) {
					return value != null && (Boolean)value? "required" : "";
				}
				
			});
			
			put("readonly", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, ComponentProperties component) {
					return value != null && (Boolean)value? "readonly" : "";
				}
			});
			
			put("componentType", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return value == null? null : "type";
				}
				
			});
			
		}});

	}
	
}
