package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class FormComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/components/form";
		
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			
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
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("content");
			add("style");
		}});
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "form-body";
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
