package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ComponentProperties;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParserImp;
import br.com.uoutec.community.ediacaran.front.theme.EmptyVarsBuilder;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;

public class TabsHeaderItemComponent extends AbstractComponent {

	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/tabs-header-item.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("title");
			add("active");
			add("icon");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{

				put("active", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return Boolean.TRUE.equals(value)? "active" : "";
					}
				});
				
				put("icon", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return value == null? "" : new TemplateVarParser("/components/icon", component.getPackageTheme(), 
								new EmptyVarsBuilder(), component.getTheme()).put("icon", value);
					}
				});
				
			}});

	}
}
