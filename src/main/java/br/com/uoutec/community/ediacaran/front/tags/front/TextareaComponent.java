package br.com.uoutec.community.ediacaran.front.tags.front;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class TextareaComponent extends ComponentFormComponent {

	
	protected void loadConfiguration() {

		TEMPLATE = "/bootstrap4/components/textfield";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_ATTRS) {{
			add("autocomplete");
			add("autofocus");
			add("maxlength");
			add("minlength");
			add("pattern");
			add("placeholder");
			add("required");
			add("cols");
			add("rows");
			remove("value");
			remove("name");
		}});
	
	
		DEFAULT_DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("autocomplete", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "on" : "off";
				}
			});

			put("required", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "readonly" : "";
				}
				
			});
			
		}});
	
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_PROPS) {{
			add("label");
			add("value");
			add("name");
			add("enabled");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_PROPERTY_PARSERS){{
				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((TextareaComponent)component).getEnabled();
						//return enabled != null && !enabled? " uneditable-input" : "";
						return "";
					}
					
				});
				
				put("label", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? new JspFragmentVarParser(((TextareaComponent)component).getJspBody()) : value;
					}
					
				});
			}});

	}
}
