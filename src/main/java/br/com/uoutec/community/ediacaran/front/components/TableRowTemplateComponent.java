package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class TableRowTemplateComponent  extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/table-row.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
			remove("style");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
			add("style");
			add("classStyle");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				put("style", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "" : "table-" + value;
					}
				});
			}});
	}
	
}
