package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class ImageComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {
		
		super.template = "/default_template/front/components/image.tmp";
		
		super.default_attrs = 
			Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
				add("src");
			}});
		
		super.default_attribute_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			}});
	
		super.default_props = 
			Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
				add("style");
				add("align");
			}});
		
		super.default_property_parsers = 
				Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
					put("style", new AttributeParserImp() {
						
						@Override
						public Object toValue(Object value, Object component) {
							if("rounded".equals(value)) {
								return " rounded.tmp";
							}
							else
							if("circle".equals(value)) {
								return " rounded-circle.tmp";
							}
							else
								return value == null? null : " img-" + value;
						}
						
					});
					
					put("align", new AttributeParserImp() {
						
						@Override
						public Object toValue(Object value, Object component) {
							if("center".equals(value)) {
								return " mx-auto d-block.tmp";
							}
							else
								return value == null? null : " float-" + value;
						}
						
					});
					
				}});
	}

}