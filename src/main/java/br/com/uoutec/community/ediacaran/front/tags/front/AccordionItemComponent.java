package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;;

public class AccordionItemComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/accordion-item.tmp";

	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});

	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("title");
			add("content");
			add("parentID");
			add("id");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
			}});
	}
	
}
