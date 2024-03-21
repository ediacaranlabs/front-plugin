package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class ImagefieldTemplateComponent extends ComponentFormTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/imagefield.tmp";
	
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("id");
			remove("name");
			remove("classStyle");
			remove("accept");
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{

		}});
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("id");
			add("name");
			add("border");
			add("width");
			add("height");
			add("src");
			add("accept");
			
			add("button");
			add("label");
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

				put("accept", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						
						if(value != null) {
							return "accept=\"" + ((String)value).replace("\"", "\\\"") + "\"";
						}
						
						return null; 
					}
					
				});
				
			}});

	}
	
}
