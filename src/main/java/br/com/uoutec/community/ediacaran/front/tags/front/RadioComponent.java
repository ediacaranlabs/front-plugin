package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.RadioTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class RadioComponent extends ComponentFormComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/components/radio";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			add("selected");
		}});
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("selected", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "checked" : "";
				}
			});
			
		}});
	
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("label");
			add("inline");
			add("enabled");
			add("label");
			//add("content");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((RadioTag)component).getEnabled();
						//return enabled != null && !enabled? " uneditable-input" : "";
						return "";
					}
					
				});
				
				put("inline", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value != null && (Boolean)value? " form-check-inline" : "";
					}
					
				});
				
				put("label", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						RadioTag tag = (RadioTag)component;
						return tag.getContent() == null? value : tag.getContent();
					}
					
				});
				

			}});
	
	}
}
