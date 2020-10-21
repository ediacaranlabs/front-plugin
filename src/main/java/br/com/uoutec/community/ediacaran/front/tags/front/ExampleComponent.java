package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;

public class ExampleComponent  extends AbstractComponent {

	
	protected void loadConfiguration() {

		TEMPLATE  = "";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			//add("");
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			/*
			put("", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value;
				}
				
				@Override
				public Object toValue(Object value) {
					return value;
				}
				
			});
			*/
			
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			//add("");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				/*
				put("", new AttributeParserImp() {
					
					@Override
					public String toName(String value) {
						return value;
					}
					
					@Override
					public Object toValue(Object value) {
						return value;
					}
					
				});
				*/
				
			}});
    
	}
}
