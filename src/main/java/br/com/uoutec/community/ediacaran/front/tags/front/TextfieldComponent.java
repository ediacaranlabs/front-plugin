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

		super.template = "/default_template/front/components/textfield.tmp";
	
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("autocomplete");
			add("autofocus");
			add("maxlength");
			add("minlength");
			add("pattern");
			add("placeholder");
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			
			put("autocomplete", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "on" : "off.tmp";
				}
			});

		}});
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("label");
			add("size");
			add("enabled");
			add("content");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{

				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((TextfieldTag)component).getEnabled();
						//return enabled != null && !enabled? " uneditable-input" : ".tmp";
						return ".tmp";
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
						return value != null? new String("form-control-").concat((String)value) : ".tmp";
					}
					
				});

			}});

	}
	
}