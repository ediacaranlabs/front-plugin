package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class IconComponent extends AbstractComponent {

	
	protected void loadConfiguration() {

		TEMPLATE       = "/bootstrap4/components/icon";
	
	
	protected void loadConfiguration() {

		TEMPLATE_STACK = "/bootstrap4/components/icon-stack";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("size");
			add("bgSize");
			add("iconSize");
			add("bg");
			add("icon");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{

				put("bgSize", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "bg-size";
					}
					
				});
				
				put("iconSize", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "icon-size";
					}
					
				});
				
			}});

	}
}
