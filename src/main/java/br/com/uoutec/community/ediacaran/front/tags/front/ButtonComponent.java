package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ButtonTag;
import br.com.uoutec.community.ediacaran.front.tags.ComponentProperties;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParserImp;
import br.com.uoutec.community.ediacaran.front.theme.EmptyVarsBuilder;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;

public class ButtonComponent extends ComponentFormComponent {
	
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
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			
			put("actionType", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return value == null? null : "type";
				}
			});

			put("action", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return value == null? null : "formaction";
				}
			});

			put("ctype", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return value == null? null : "formenctype";
				}
			});

			put("method", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return value == null? null : "formmethod";
				}
			});

			put("target", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return value == null? null : "formtarget";
				}
			});
			
		}});

	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("label");
			add("size");
			add("type");
			add("block");
			add("outline");
			add("classStyle");
			add("icon");
			add("content");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
				
				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return value == null? "" : new String(" btn-").concat(String.valueOf(value));
					}
				});

				put("type", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						Boolean outline = ((ButtonTag)component).getOutline();
						return new String(" btn-").concat(outline != null && outline ? "outline-" : "").concat(value == null? "primary" : String.valueOf(value));
					}
				});
				
				put("block", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return value == null? "" : (Boolean)value? " btn-block" : "";
					}
				});
				
				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						Boolean enabled = ((ButtonTag)component).getEnabled();
						return enabled != null && !enabled? " disabled" : "";
					}
				});

				put("icon", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return value == null? "" : new TemplateVarParser("/components/icon", component.getPackageTheme(), 
								new EmptyVarsBuilder(), component.getTheme()).put("icon", value);
					}
				});
				
			}});
	}
}