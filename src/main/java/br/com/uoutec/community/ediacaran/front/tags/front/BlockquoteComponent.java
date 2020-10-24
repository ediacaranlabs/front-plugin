package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.BlockquoteTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class BlockquoteComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/default_template/front/components/blockquote.tmp";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("content");
			add("cite");
		}});
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				
				put("cite", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						BlockquoteTag tag = (BlockquoteTag)component;
						return value == null? null : new TemplateVarParser("/components/cite", tag.getTemaPackage(), null, tag.getTema()).put("content", value);
					}
				});
				
			}});
	}
	
}
