package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.BreadcrumbPathTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.ComponentVarsBuilder;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class BreadcrumbPathComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/components/breadcrumb-path.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("icon");
			add("text");
			add("lnk");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{
				put("icon", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						BreadcrumbPathTag tag = (BreadcrumbPathTag)component;
						ComponentVarsBuilder cv = new ComponentVarsBuilder();
						return value == null || ((String)value).isEmpty()? null : new TemplateVarParser("/components/icon", tag.getTemaPackage(), cv, tag.getTheme()).put("icon", value);
					}
				});
			}});
    
	}
}