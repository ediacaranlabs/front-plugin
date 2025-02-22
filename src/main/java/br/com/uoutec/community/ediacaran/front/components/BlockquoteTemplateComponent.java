package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;
import br.com.uoutec.community.ediacaran.front.theme.ComponentVarsBuilder;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;

public class BlockquoteTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/blockquote.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("src");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			
			put("src", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return "cite";
				}
				
			});
			
		}});

		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
			add("cite");
			add("src");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				
				put("cite", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						ComponentVarsBuilder cvb = new ComponentVarsBuilder();
						cvb.put("content", value);
						cvb.put("src", component.getProperty("src") == null? "#" : component.getProperty("src"));
						return value == null? null : new TemplateVarParser("/components/cite", component.getPackageTheme(), 
								cvb, component.getTheme());
					}
				});
				
			}});
	}
	
}
