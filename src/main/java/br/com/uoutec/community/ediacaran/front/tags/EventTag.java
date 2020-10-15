package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class EventTag extends AbstractSimpleTag {

	public static final String TEMPLATE  = "/bootstrap4/components/event";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractBodyTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractBodyTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractBodyTag.DEFAULT_PROPS) {{
			add("content");
			add("type");
			add("component");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractBodyTag.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((EventTag)component).getJspBody());
					}
				});
		}});
	
	private String type;
	
	private String component;
	
	private JspFragmentVarParser content;


    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComponent() {
		if(component == null) {
			Object cp = super.getParentTag();
			if(cp != null) {
				if(cp instanceof AbstractSimpleTag) {
					component = ((AbstractSimpleTag)cp).getId();
				}
				else
				if(cp instanceof AbstractBodyTag) {
					component = ((AbstractBodyTag)cp).getId();
				}
			}
		}
		
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }

    protected Set<String> getEmptyAttributes(){
    	return DEFAULT_EMPTY_ATTRIBUTES;
    }
    
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

    protected Set<String> getDefaultProperties(){
    	return DEFAULT_PROPS;
    }

    protected Map<String, AttributeParser> getPropertyParsers(){
    	return DEFAULT_PROPERTY_PARSERS;
    }
    
}
