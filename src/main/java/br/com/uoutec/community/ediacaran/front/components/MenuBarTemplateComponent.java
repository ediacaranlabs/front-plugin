package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class MenuBarTemplateComponent  extends AbstractTemplateComponent {

	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/menu-bar.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("classStyle");
			add("style");
			add("background");
			add("position");
			add("content");
			add("expand");
			add("sidebar");
			add("id");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				put("style", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? null : "navbar-" + value;
					}
				});
				
				put("background", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? null : "bg-" + value;
					}
				});
				
				put("position", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						
						if(value == null) {
							return null;
						}
						
						switch ((String)value) {
						case "top":
						case "bottom":
							return "fixed-" + value;
						case "sticky":
							return "sticky-top";
						default:
							return value;
						}
					}
				});

				put("expand", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? null : "navbar-expand-" + value;
					}
					
				});

				put("sidebar", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "" : "sidebar";
					}
					
				});
				
			}});

	}
}
