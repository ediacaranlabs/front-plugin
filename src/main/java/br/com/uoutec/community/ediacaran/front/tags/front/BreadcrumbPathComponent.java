package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class BreadcrumbPathComponent extends AbstractComponent {

	
	protected void loadConfiguration() {

		TEMPLATE = "/bootstrap4/components/breadcrumb-path";
	
	
	protected void loadConfiguration() {

		TEMPLATE_ICON = "/bootstrap4/components/icon";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("icon");
			add("text");
			add("lnk");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("icon", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						BreadcrumbPathComponent tag = (BreadcrumbPathComponent)component;
				    	return value == null? null : new TemplateVarParser(TEMPLATE_ICON, tag.getTemaPackage(), tag.getTema()).put("icon", value).put("size", 1);
					}
				});
			}});
    
	}
}