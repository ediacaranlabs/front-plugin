package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class ForTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/for.tmp";
	
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>());
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>());
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("var");
			add("begin");
			add("end");
			add("step");
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

					put("begin", new PropertyParserImp() {
						
						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {
							return FrontCodeUtil.toSimpleCode((String)value);
						}
						
					});

					put("end", new PropertyParserImp() {
						
						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {
							return FrontCodeUtil.toSimpleCode((String)value);
						}
						
					});

					put("step", new PropertyParserImp() {
						
						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {
							return FrontCodeUtil.toSimpleCode((String)value);
						}
						
					});
					
				}});

	}
	
}
