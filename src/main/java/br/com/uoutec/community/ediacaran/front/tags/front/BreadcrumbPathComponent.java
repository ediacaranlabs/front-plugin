package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.PropertiesComponentTemplate;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;
import br.com.uoutec.community.ediacaran.front.theme.ComponentVarsBuilder;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;

public class BreadcrumbPathComponent extends AbstractTemplateComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/breadcrumb-path.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("icon");
			add("text");
			add("lnk");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(super.default_property_parsers){{
				put("icon", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						ComponentVarsBuilder cv = new ComponentVarsBuilder();
						return value == null || ((String)value).isEmpty()? null : new TemplateVarParser("/components/icon", component.getPackageTheme(), cv, component.getTheme()).put("icon", value);
					}
				});
			}});
    
	}
}