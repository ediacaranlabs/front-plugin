package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class ImageComponent extends AbstractComponent {

	
	protected void loadConfiguration() {
		
		TEMPLATE  = "/default_template/front/components/image.tmp";
		
		DEFAULT_ATTRS = 
			Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
				add("src");
			}});
		
		DEFAULT_DEFAULT_ATTRIBUTE_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			}});
	
		DEFAULT_PROPS = 
			Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
				add("style");
				add("align");
			}});
		
		DEFAULT_PROPERTY_PARSERS = 
				Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
					put("style", new AttributeParserImp() {
						
						@Override
						public Object toValue(Object value, Object component) {
							if("rounded".equals(value)) {
								return " rounded";
							}
							else
							if("circle".equals(value)) {
								return " rounded-circle";
							}
							else
								return value == null? null : " img-" + value;
						}
						
					});
					
					put("align", new AttributeParserImp() {
						
						@Override
						public Object toValue(Object value, Object component) {
							if("center".equals(value)) {
								return " mx-auto d-block";
							}
							else
								return value == null? null : " float-" + value;
						}
						
					});
					
				}});
	}

	}
}
