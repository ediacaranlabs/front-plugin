package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class FormTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/form.tmp";
		
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
			remove("style");
			add("acceptCharset");
			add("action");
			add("enctype");
			add("method");
			add("target");
			add("update");
		}});
		
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			
			put("acceptCharset", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return value == null? null : "accept-charset";
				}
			});

			put("update", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return "dest-content";
				}
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return value;
				}
			});
			
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
			add("style");
			add("classStyle");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				put("content", new PropertyParserImp() {
					
					@Override
					public String toName(String value, PropertiesComponentTemplate component) {
						return "form-body";
					}
					
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value;
					}
					
				});
				
				put("style", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return "inline".equals(value)? "form-inline " : "";
					}
				});
				
				
			}});
	
	}
}
