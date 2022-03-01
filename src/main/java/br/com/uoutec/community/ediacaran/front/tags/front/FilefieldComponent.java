package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ComponentProperties;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParserImp;

public class FilefieldComponent extends ComponentFormComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {
		super.loadConfiguration();

		super.template = "/default_template/front/components/filefield.tmp";
	
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("accept");
			add("capture");
			add("multiple");
			remove("classStyle");
		}});
	
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			
			put("capture", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, ComponentProperties component) {
					return value != null && ((Boolean)value)?  "capture" : null;
				}
			});

			put("multiple", new AttributeParserImp() {
				
				@Override
				public String toName(String value, ComponentProperties component) {
					return null;
				}
				
				@Override
				public Object toValue(Object value, ComponentProperties component) {
					return value != null && ((Boolean)value)?  "multiple" : null;
				}
			});
			
		}});
	
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("label");
			add("enabled");
			add("content");
			add("classStyle");
		}});
	
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{

				put("enabled", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return "";
					}
					
				});

				put("label", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, ComponentProperties component) {
						return value;
					}
					
				});

			}});

	}
	
}
