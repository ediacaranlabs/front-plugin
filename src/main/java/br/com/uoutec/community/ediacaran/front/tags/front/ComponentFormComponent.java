package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public abstract class ComponentFormComponent extends AbstractComponent {

	public static final String FORM = ComponentFormComponent.class.getSimpleName() + ":form";
	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/components/form-group";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			add("name");
			add("value");
			add("required");
			add("enabled");
			add("readonly");
			add("componentType");
			add("form");
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("enabled", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && !(Boolean)value? "disabled" : "";
				}
			});

			put("required", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "required" : "";
				}
				
			});
			
			put("readonly", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "readonly" : "";
				}
			});
			
			put("componentType", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "type";
				}
				
			});
			
		}});

	}
	
}
