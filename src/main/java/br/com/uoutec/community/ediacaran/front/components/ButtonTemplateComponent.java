package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ButtonTagComponent;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;
import br.com.uoutec.community.ediacaran.front.theme.EmptyVarsBuilder;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;

public class ButtonTemplateComponent extends ComponentFormTemplateComponent {
	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();
		
		super.template = "/default_template/front/components/button.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("action");
			add("ctype");
			add("method");
			add("target");
			add("actionType");
			add("update");
			remove("classStyle");
			remove("style");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
			
			put("actionType", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return value == null? null : "type";
				}
			});

			put("action", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return value == null? null : "formaction";
				}
			});

			put("ctype", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return value == null? null : "formenctype";
				}
			});

			put("method", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return value == null? null : "formmethod";
				}
			});

			put("target", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return value == null? null : "formtarget";
				}
			});
			
			put("update", new PropertyParserImp() {
				
				@Override
				public String toName(String value, PropertiesComponentTemplate component) {
					return "dest-content";
				}
				
				@Override
				public Object toValue(Object value, PropertiesComponentTemplate component) {
					return value;
				}
			});
			
		}});

	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("label");
			add("size");
			add("style");
			add("block");
			add("outline");
			add("classStyle");
			add("icon");
			add("content");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				
				put("size", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "" : new String(" btn-").concat(String.valueOf(value));
					}
				});

				put("style", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						Boolean outline = (Boolean)component.getProperty("outline");
						return new String(" btn-").concat(outline != null && outline ? "outline-" : "").concat(value == null? "primary" : String.valueOf(value));
					}
				});
				
				put("block", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "" : (Boolean)value? " btn-block" : "";
					}
				});
				
				put("enabled", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						Boolean enabled = ((ButtonTagComponent)component).getEnabled();
						return enabled != null && !enabled? " disabled" : "";
					}
				});

				put("icon", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "" : new TemplateVarParser("/components/icon", component.getPackageTheme(), 
								new EmptyVarsBuilder(), component.getTheme()).put("icon", value);
					}
				});
				
			}});
	}
}