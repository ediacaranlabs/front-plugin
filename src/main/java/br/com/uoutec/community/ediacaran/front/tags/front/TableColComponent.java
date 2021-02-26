package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.system.theme.AttributeParserImp;

public class TableColComponent  extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/table-col.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			add("cols");
			add("rows");
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			put("cols", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return "colspan";
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Integer)value > 0 ? (Integer)value: "";
				}
			});
			
			put("rows", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return "rowspan";
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Integer)value > 0 ? (Integer)value: "";
				}
			});
			
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("content");
			add("style");
			add("classStyle");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? "" : "table-col-" + value;
					}
				});
			}});

	}
}
