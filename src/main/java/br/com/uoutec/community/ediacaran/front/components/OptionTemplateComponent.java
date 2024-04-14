package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class OptionTemplateComponent extends ComponentFormTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/option.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("selected");
			add("label");
			remove("name");
			remove("componentType");
			remove("form");
			remove("required");
			remove("enabled");
			remove("readonly");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			
			put("enabled", new PropertyParserImp() {
				
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
						return "";
					}
					else
					if("false".equals(String.valueOf(value))) {
						return "disabled";
					}
					else
					if(FrontCodeUtil.isCode(String.valueOf(value))) {
						return "!{( " + FrontCodeUtil.toRequiredCode(String.valueOf(value)) + " )? \"\" : \" disabled \"}"; 
					}
					
					return "";
				}
				
			});

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
						return "selected";
					}
					else
					if("false".equals(String.valueOf(value))) {
						return "";
					}
					else
					if(FrontCodeUtil.isCode(String.valueOf(value))) {
						return "!{( " + FrontCodeUtil.toRequiredCode(String.valueOf(value)) + " )? \" selected \" : \"\"}"; 
					}
					
					return "";
				}
				
			});

		}});
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers));

	}
}
