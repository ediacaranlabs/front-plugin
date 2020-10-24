package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class IconComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		super.template = "/default_template/front/components/icon.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
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
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{

				put("bgSize", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "bg-size.tmp";
					}
					
				});
				
				put("iconSize", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "icon-size.tmp";
					}
					
				});
				
			}});

	}
}