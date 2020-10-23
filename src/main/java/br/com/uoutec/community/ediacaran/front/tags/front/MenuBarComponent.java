package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class MenuBarComponent  extends AbstractComponent {

	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE  = "/components/menu-bar";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("style");
			add("background");
			add("position");
			add("content");
			add("expand");
			add("sidebar");
		}});
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? null : "navbar-" + value;
					}
				});
				
				put("background", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? null : "bg-" + value;
					}
				});
				
				put("position", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						
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

				put("expand", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "navbar-expand-lg" : "navbar-expand-" + value;
					}
					
				});

				put("sidebar", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "" : "sidebar";
					}
					
				});
				
			}});

	}
}
