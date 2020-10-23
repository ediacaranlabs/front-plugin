package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class OptionComponent extends ComponentFormComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/components/option";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			add("selected");
			add("label");
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
					return value != null && !(Boolean)value? "" : "disabled";
				}
				
			});

			put("selected", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "selected" : "";
				}
				
			});

		}});
	
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("content");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS));

	}
}
