package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class CheckboxComponent extends ComponentFormComponent {

	
	protected void loadConfiguration() {

		TEMPLATE = "/bootstrap4/components/checkbox";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_ATTRS) {{
			add("selected");
		}});
	
	
		DEFAULT_DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_ATTRIBUTE_PARSERS){{
			
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
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_PROPS) {{
			add("inline");
			add("content");
			add("label");
			add("enabled");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_PROPERTY_PARSERS){{

				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((CheckboxComponent)component).getEnabled();
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
						return value;
					}
				});
				
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						JspFragment jspBody = ((CheckboxComponent)component).getJspBody();
						return jspBody == null? null : new JspFragmentVarParser(jspBody);
					}
				});
				
			}});

	}
}