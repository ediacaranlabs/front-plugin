package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class DescriptionComponent  extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/default_template/front/components/description.tmp";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("title");
			add("truncate");
			add("titleWidth");
			add("contentWidth");
			add("content");
		}});
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("truncate", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return "text-truncate.tmp";
					}
					
				});
				
				put("titleWidth", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? 3 : value;
					}
					
				});
				
				put("contentWidth", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? 9 : value;
					}
					
				});
				
			}});
	}
}
