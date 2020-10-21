package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class DropdownTag  extends ComponentFormComponent {

	
	protected void loadConfiguration() {

		TEMPLATE  = "/bootstrap4/components/dropdown";
	
	
	protected void loadConfiguration() {

		TEMPLATE2 = "/bootstrap4/components/split-dropdown";
	
	
	protected void loadConfiguration() {

		TEMPLATE_WRAPPER = "/bootstrap4/components/dropdown-wrapper";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_ATTRS) {{
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_PROPS) {{
			add("label");
			add("style");
			add("size");
			add("split");
			add("variation");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_PROPERTY_PARSERS){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return " btn-" + value;
					}
					
				});
				
				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return " btn-" + value;
					}
					
				});
				
				put("variation", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return " drop" + value;
					}
					
				});
				
			}});
	}
}
