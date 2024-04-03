package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class ForEachTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/foreach.tmp";
	
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>());
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>());
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("var");
			add("items");
			add("content");
		}});
	
	
		super.default_property_parsers = 
				Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{

					put("var", new PropertyParserImp() {
						
						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {
							return FrontCodeUtil.toSimpleCode((String)value);
						}
						
					});

					put("items", new PropertyParserImp() {
						
						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {
							return FrontCodeUtil.toRequiredCode((String)value);
						}
						
					});

				}});

	}
	
}
