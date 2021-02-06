package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;

public class FlotChartsSeriesComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/flotcharts-series.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("flotchartid");
			add("label");
			add("color");
			add("lines");
			add("bars");
			add("points");
			add("xaxis");
			add("yaxis");
			add("clickable");
			add("hoverable");
			add("shadowSize");
			add("highlightColor");
			add("content");
			add("classStyle");
			add("id");
		}});
	
		super.default_property_parsers = 
				Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
					
				}});
	}
	
}
