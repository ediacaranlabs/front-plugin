package br.com.uoutec.community.ediacaran.front.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;

public class FlotChartsTemplateComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/flotcharts.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("src");
			add("updateFrequence");
			add("updateFrequenceUnit");
			add("content");
			add("classStyle");
			add("id");
		}});
	
		super.default_property_parsers = 
				Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
					
					put("updateFrequence", new PropertyParserImp() {

						@Override
						public Object toValue(Object value, PropertiesComponentTemplate component) {

							int time = value == null? -1 : (Integer)value;
							TimeUnit tm = component.getProperty("updateFrequenceUnit") == null? 
									TimeUnit.SECONDS : 
									TimeUnit.valueOf((String)component.getProperty("updateFrequenceUnit"));
							
							return time > 0? tm.toMillis(time) : -1;
						}
						
					});
					
				}});
	}
	
}
