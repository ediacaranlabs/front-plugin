package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class BlockquoteComponent extends AbstractComponent {

	
	protected void loadConfiguration() {

		TEMPLATE      = "/bootstrap4/components/blockquote";
	
	public static final String CITE_TEMPLATE = "/bootstrap4/components/cite";
	
	
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
						BlockquoteComponent tag = (BlockquoteComponent)component;
						return value == null? null : new TemplateVarParser(CITE_TEMPLATE, tag.getTemaPackage(), tag.getTema()).put("content", value);
					}
				});
				
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((BlockquoteComponent)component).getJspBody());
					}
				});
			}});
	
}
