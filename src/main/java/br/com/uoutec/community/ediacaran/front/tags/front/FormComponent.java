package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.system.theme.AttributeParserImp;

public class FormComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/form.tmp";
		
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
			add("acceptCharset");
			add("action");
			add("enctype");
			add("method");
			add("target");
			add("update");
		}});
		
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			
			put("acceptCharset", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "accept-charset";
				}
			});

			put("update", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return "dest-content";
				}
				
				@Override
				public Object toValue(Object value, Object component) {
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
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
				put("content", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "form-body";
					}
					
					public Object toValue(Object value, Object component) {
						return value;
					}
					
				});
				
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return "inline".equals(value)? "form-inline " : "";
					}
				});
				
				
			}});
	
	}
}
