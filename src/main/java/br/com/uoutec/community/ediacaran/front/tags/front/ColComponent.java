package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ColTag;
import br.com.uoutec.community.ediacaran.system.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.system.theme.AttributeParserImp;

public class ColComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	public void loadConfiguration() {

		super.template = "/default_template/front/designer/col.tmp";
	
		super.default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_attrs) {{
			remove("classStyle");
		}});
	
		super.default_attribute_parsers = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_attribute_parsers){{
			remove("classStyle");
		}});
	
		super.default_props = 
		Collections.unmodifiableSet(new HashSet<String>(super.default_props) {{
			add("size");
			add("content");
		}});
	
		super.default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.default_property_parsers){{

				put("size", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						ColTag c = (ColTag)component;
						
						Integer size   = c.getSize();
						Integer offset = c.getOffset();
						Integer order  = c.getOrder();
						
						String sizeStyle   = size == null?   "col" : "col-sm-12 col-md-12 " + "col-lg-" + size + " col-xl-" + size;
						String offsetStyle = offset == null? ""    : "offset-lg-" + offset + " offset-xl-" + offset;
						String orderStyle  = order == null?  ""    : "order-lg-" + order + " order-xl-" + order;
						
						return sizeStyle + " " + offsetStyle + " " + orderStyle;
					}
				});
				
			}});
	}
}
