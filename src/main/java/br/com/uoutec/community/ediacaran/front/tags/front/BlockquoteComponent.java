package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.BlockquoteTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.ComponentVarsBuilder;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class BlockquoteComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/blockquote.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});

		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
			add("cite");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
				
				put("cite", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						BlockquoteTag tag = (BlockquoteTag)component;
						ComponentVarsBuilder cv = new ComponentVarsBuilder();
						//cv.put("content", tag.getCite());
						return value == null? null : new TemplateVarParser("/components/cite", tag.getTemaPackage(), cv, tag.getTheme()).put("content", value);
					}
				});
				
			}});
	}
	
}
