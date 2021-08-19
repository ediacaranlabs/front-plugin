package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParserImp;

public class ModalComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/modal.tmp";
	
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
			add("classStyle");
			add("fade");
		}});
	
		super.default_property_parsers = 
				Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
					
					put("style", new AttributeParserImp() {
						
						@Override
						public Object toValue(Object value, Object component) {
							return value == null? " modal" : " modal-" + value;
						}
						
					});

					put("fade", new AttributeParserImp() {
						
						@Override
						public Object toValue(Object value, Object component) {
							return value != null && (Boolean)value? " fade" : null;
						}
						
					});
					
				}});
	}
	
}
