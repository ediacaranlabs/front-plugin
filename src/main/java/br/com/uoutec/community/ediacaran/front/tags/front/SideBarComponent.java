package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class SideBarComponent  extends AbstractComponent {
	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/default_template/front/components/sidebar.tmp";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("size");
			add("align");
			add("content");
		}});
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("size", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return null;
					}
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "col-lg-1 col-xl-1" : "col-lg-" + value + " col-xl-" + value;
					}
				});
				
			}});

	}
}
