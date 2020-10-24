package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import br.com.uoutec.community.ediacaran.front.tags.ColTag;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class ColComponent extends AbstractComponent {

	
	@SuppressWarnings("serial")
	protected void loadConfiguration() {

		TEMPLATE = "/default_template/front/designer/col.tmp";
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			remove("classStyle");
		}});
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			remove("classStyle");
		}});
	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("size");
			add("content");
		}});
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{

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
