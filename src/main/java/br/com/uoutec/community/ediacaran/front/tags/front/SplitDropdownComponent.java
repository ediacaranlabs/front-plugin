package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ComponentProperties;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParserImp;

public class SplitDropdownComponent  extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/split-dropdown.tmp";

		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});

	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("itens");
			add("label");
			add("style");
			add("size");
			add("split");
			add("variation");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return " btn-" + value;
					}
					
				});
				
				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return " btn-" + value;
					}
					
				});
				
				put("variation", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return " drop" + value;
					}
					
				});
				
			}});
	}
}
