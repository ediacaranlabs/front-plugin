package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.BreadcrumbPathTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public class BreadcrumbPathComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/default_template/front/components/breadcrumb-path.tmp";
	
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
						BreadcrumbPathTag tag = (BreadcrumbPathTag)component;
						return value == null? null : new TemplateVarParser("/components/icon", tag.getTemaPackage(), null, tag.getTema()).put("icon", value).put("size", 1);
					}
				});
			}});
    
	}
}