package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class MenuBarComponent  extends AbstractComponent {

	public static final String CONTEXT_ID = MenuBarComponent.class.getName() + ":CONTEXT";
	
	
	protected void loadConfiguration() {

		TEMPLATE  = "/bootstrap4/components/menu-bar";
	
	
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

				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((MenuBarComponent)component).getJspBody());
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
