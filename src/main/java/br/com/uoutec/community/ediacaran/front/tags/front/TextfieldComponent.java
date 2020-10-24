package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.TextfieldTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class TextfieldComponent extends ComponentFormComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/components/textfield";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			add("autocomplete");
			add("autofocus");
			add("maxlength");
			add("minlength");
			add("pattern");
			add("placeholder");
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("autocomplete", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "on" : "off";
				}
			});

		}});
	
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("label");
			add("size");
			add("enabled");
			add("content");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{

				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((TextfieldTag)component).getEnabled();
						//return enabled != null && !enabled? " uneditable-input" : "";
						return "";
					}
					
				});

				put("label", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value;
					}
					
				});

				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value != null? new String("form-control-").concat((String)value) : "";
					}
					
				});

			}});

	}
	
}
