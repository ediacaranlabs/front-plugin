package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ButtonTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class ButtonComponent extends ComponentFormComponent {
	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/components/button";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			add("action");
			add("ctype");
			add("method");
			add("target");
			add("actionType");
			remove("classStyle");
		}});
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("actionType", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "type";
				}
			});

			put("action", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formaction";
				}
			});

			put("ctype", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formenctype";
				}
			});

			put("method", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formmethod";
				}
			});

			put("target", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "formtarget";
				}
			});
			
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("label");
			add("size");
			add("type");
			add("block");
			add("outline");
			add("classStyle");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				
				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "" : new String(" btn-").concat(String.valueOf(value));
					}
				});

				put("type", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean outline = ((ButtonTag)component).getOutline();
						return new String(" btn-").concat(outline != null && outline ? "outline-" : "").concat(value == null? "primary" : String.valueOf(value));
					}
				});
				
				put("block", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "" : (Boolean)value? " btn-block" : "";
					}
				});
				
				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((ButtonTag)component).getEnabled();
						return enabled != null && !enabled? " disabled" : "";
					}
				});
				
			}});
	}
}