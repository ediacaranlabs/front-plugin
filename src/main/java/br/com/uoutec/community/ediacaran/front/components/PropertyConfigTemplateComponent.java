package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;

public class PropertyConfigTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/property-config.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("id");
			add("name");
			add("content");
			add("parentID");
		}});
	
		super.default_property_parsers = 
				Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				}});
	}
	
}
