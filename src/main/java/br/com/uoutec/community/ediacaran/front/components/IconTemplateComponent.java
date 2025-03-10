package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class IconTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/icon.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("size");
			add("bgSize");
			add("iconSize");
			add("bg");
			add("icon");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{

				put("bgSize", new PropertyParserImp() {
					
					@Override
					public String toName(String value, PropertiesComponentTemplate component) {
						return "bg-size";
					}
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "2" : String.valueOf(value);
					}
					
				});
				
				put("iconSize", new PropertyParserImp() {
					
					@Override
					public String toName(String value, PropertiesComponentTemplate component) {
						return "icon-size";
					}
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "1" : String.valueOf(value);
					}
					
				});
				
			}});

	}
}
