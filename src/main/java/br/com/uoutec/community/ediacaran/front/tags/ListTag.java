package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class ListTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE   = "/bootstrap4/components/list";
	
	public static final String TEMPLATE2  = "/bootstrap4/components/ordered-list";
	
	public static final String PARENT = ListTag.class.getSimpleName() + ":PARENT";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleComponent.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleComponent.DEFAULT_PROPS) {{
			add("style");
			add("content");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_PROPERTY_PARSERS){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? null : "list-" + value;
					}
					
				});
				
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((ListTag)component).getJspBody());
					}
					
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String style; //inline, unstyled, ordered
	
	private JspFragmentVarParser content;
	
	public ListTag() {
	}
	
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	getJspContext().setAttribute(PARENT, this);
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	getJspContext().removeAttribute(PARENT);
    }

    protected String getDefaultTemplate() {
    	return "ordered".equals(this.style)? TEMPLATE2 : TEMPLATE;
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
