package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class DescriptionComponent  extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		super.template = "/default_template/front/components/description.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("title");
			add("truncate");
			add("titleWidth");
			add("contentWidth");
			add("content");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
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
