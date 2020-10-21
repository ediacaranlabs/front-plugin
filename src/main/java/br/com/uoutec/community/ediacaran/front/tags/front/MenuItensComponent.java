package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class MenuItensComponent  extends AbstractComponent {

	
	protected void loadConfiguration() {

		TEMPLATE  = "/bootstrap4/components/menu-itens";
	
	
	protected void loadConfiguration() {

		TEMPLATE_2  = "/bootstrap4/components/menu-itens-menu";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("content");
			add("align");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((MenuItensComponent)component).getJspBody());
					}
					
				});
				
				put("align", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						if(value == null) {
							return null;
						}
						
						return "left".equals(value)? " mr-auto" : " ml-auto";
					}
					
				});
			}});

	}
}
