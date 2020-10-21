package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.tagext.JspFragment;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class TableColComponent  extends AbstractComponent {

	
	protected void loadConfiguration() {

		TEMPLATE  = "/bootstrap4/components/table-col-header";
	
	
	protected void loadConfiguration() {

		TEMPLATE_2  = "/bootstrap4/components/table-col";
	
	
		DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_ATTRS) {{
			add("size");
		}});
	
	
		DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_ATTRIBUTE_PARSERS){{
			put("size", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return "colspan";
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Integer)value > 1 ? (Integer)value - 1: "";
				}
			});
		}});

	
		DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(super.DEFAULT_PROPS) {{
			add("content");
		}});
	
	
		DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(super.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						JspFragment jspBody = ((TableColComponent)component).getJspBody();
						return jspBody == null? null : new JspFragmentVarParser(((TableColComponent)component).getJspBody());
					}
					
				});
			}});

	}
}
