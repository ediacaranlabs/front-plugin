package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FieldValidatorRuleParamTag extends AbstractSimpleTag {

	public static final String TEMPLATE = "/bootstrap4/components/select";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_PROPS) {{
			add("value");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_PROPERTY_PARSERS){{
				put("value", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((FieldValidatorRuleParamTag)component).getJspBody());
					}
				});
			}});
	
	private String name;
	
	private JspFragmentVarParser value;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JspFragmentVarParser getValue() {
		return value;
	}

	public void setValue(JspFragmentVarParser value) {
		this.value = value;
	}
    
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
}
