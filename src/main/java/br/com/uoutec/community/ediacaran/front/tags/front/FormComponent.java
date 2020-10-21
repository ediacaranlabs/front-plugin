package br.com.uoutec.community.ediacaran.front.tags.front;

import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class FormComponent extends AbstractComponent {

	
	protected void loadConfiguration() {

		TEMPLATE = "/bootstrap4/components/form";
	
	public static final String FORM = FormComponent.class.getSimpleName() + ":form";
	
	public static final String VERTICAL_FORM = FormComponent.class.getSimpleName() + ":vertical_form";
	
	public static final String VERTICAL_FORM_VALUE = "true";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			add("acceptCharset");
			add("action");
			add("enctype");
			add("method");
			add("target");
			add("update");
		}});
	
	
		DEFAULT_DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("acceptCharset", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "accept-charset";
				}
			});

			put("update", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return "dest-content";
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value;
				}
			});
			
		}});
	
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("content");
			add("style");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "form-body";
					}
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((FormComponent)component).getJspBody());
					}
				});
				
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return "inline".equals(value)? "form-inline " : "";
					}
				});
				
				
			}});
	
	}
}
