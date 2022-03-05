package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;

public class ModalHeaderTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/modal-header.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});

	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers));
	}
}