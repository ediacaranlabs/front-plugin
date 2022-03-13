package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class ImageTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		
		super.template = "/default_template/front/components/image.tmp";
		
		super.default_attrs = 
			Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
				remove("classStyle");
				remove("style");
				add("src");
			}});
		
		super.default_attribute_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			}});
	
		super.default_props = 
			Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
				add("style");
			}});
		
		super.default_property_parsers = 
				Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
					put("style", new PropertyParserImp() {
						
						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {
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
					
					put("align", new PropertyParserImp() {
						
						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {
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
