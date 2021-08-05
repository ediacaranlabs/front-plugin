package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.TestimonialTag;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParserImp;
import br.com.uoutec.community.ediacaran.front.theme.ComponentVarsBuilder;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;

public class TestimonialComponent extends AbstractComponent {
	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/testimonial.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
			add("image");
			add("name");
			add("info");
			add("classStyle");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
				
				put("image", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						TestimonialTag tag = (TestimonialTag)component;
						ComponentVarsBuilder cvb = new ComponentVarsBuilder();
						cvb
							.put("attr", "src=\"" + value + "\"")
							.put("style", "rounded-circle");
						return value == null? null : new TemplateVarParser("/components/image", tag.getThemePackage(), 
								cvb, tag.getTheme());
					}
				});

				
			}});
	}
}