package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class RadioTemplateComponent extends ComponentFormTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/radio.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("selected");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			
			put("selected", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					if(value == null) {
						return null;
					}
					else
					if("true".equals(String.valueOf(value))) {
						return "checked";
					}
					else
					if("false".equals(String.valueOf(value))) {
						return "";
					}
					else
					if(FrontCodeUtil.isCode(String.valueOf(value))) {
						return "!{( " + FrontCodeUtil.toRequiredCode(String.valueOf(value)) + " )? \"\" : \" checked \"}"; 
					}
					
					return "";
				}
			});
			
		}});
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("label");
			add("inline");
			add("enabled");
			add("label");
			add("content");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				put("enabled", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return "";
					}
					
				});
				
				put("inline", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value != null && (Boolean)value? " form-check-inline" : "";
					}
					
				});
				
			}});
	
	}
}
