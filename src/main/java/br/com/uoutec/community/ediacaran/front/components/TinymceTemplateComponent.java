package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class TinymceTemplateComponent extends ComponentFormTemplateComponent {
	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();
		
		super.template = "/default_template/front/components/tinymce.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("maxlength");
			add("minlength");
			add("required");
			remove("value");
			remove("componentType");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			put("required", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return value != null && (Boolean)value? "readonly" : "";
				}
				
			});
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("id");
			add("label");
			add("value");
			add("name");
			add("enabled");
			add("content");
		}});
	
		super.default_property_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
			put("enabled", new PropertyParserImp() {
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return "";
				}
				
			});
		}});

	}
}