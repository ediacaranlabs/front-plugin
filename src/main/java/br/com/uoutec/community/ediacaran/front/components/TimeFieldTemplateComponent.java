package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class TimeFieldTemplateComponent extends ComponentFormTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/timefield.tmp";
	
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("max");
			add("min");
			add("autocomplete");
			add("autofocus");
			remove("classStyle");
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			
			put("autocomplete", new PropertyParserImp() {
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return value != null && (Boolean)value? "on" : "off";
				}
			});

		}});
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("label");
			add("size");
			add("enabled");
			add("content");
			add("classStyle");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{

				put("enabled", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return "";
					}
					
				});

				put("label", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value;
					}
					
				});

				put("size", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value != null? new String("form-control-").concat((String)value) : "";
					}
					
				});

			}});

	}
	
}
