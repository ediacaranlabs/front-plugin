package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class SideBarTemplateComponent  extends AbstractTemplateComponent {
	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/sidebar.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("size");
			add("align");
			add("content");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				put("size", new PropertyParserImp() {
					
					@Override
					public String toName(String value, PropertiesComponentTemplate component) {
						return null;
					}
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "col-lg-1 col-xl-1" : "col-lg-" + value + " col-xl-" + value;
					}
				});
				
			}});

	}
}
