package br.com.uoutec.community.ediacaran.front.tags.front;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class SelectComponent extends ComponentFormComponent {

	
	protected void loadConfiguration() {

		TEMPLATE = "/bootstrap4/components/select";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_ATTRS) {{
			add("autofocus");
			add("required");
			add("multiple");
			add("sizeList");
		}});
	
	
		DEFAULT_DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("required", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "required" : "";
				}
				
			});

			put("sizeList", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "size";
				}
				
			});
			
			put("multiple", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Boolean)value? "multiple" : "";
				}
				
			});
			
		}});
	
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormComponent.DEFAULT_PROPS) {{
			add("label");
			add("size");
			add("options");
			add("enabled");
			add("readonly");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormComponent.DEFAULT_PROPERTY_PARSERS){{
				
				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						Boolean enabled = ((SelectComponent)component).getEnabled();
						//return enabled != null && !enabled? " uneditable-input" : "";
						return "";
					}
					
				});
				
				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value != null? new String("form-control-").concat((String)value) : "";
					}
					
				});
				
				put("options", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						JspFragment jspBody = ((SelectComponent)component).getJspBody();
						return jspBody == null? null : new JspFragmentVarParser(jspBody);
					}
				});
				
			}});

	}
}
