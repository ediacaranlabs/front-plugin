package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;

public class IfTemplateComponent extends ComponentFormTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/if.tmp";
	
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>());
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>());
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("test");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>());

	}
	
}
