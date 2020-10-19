package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class ListItemTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/bootstrap4/components/list-item";
	
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
			add("inline");
			add("content");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_PROPERTY_PARSERS){{
				put("inline", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value != null && (Boolean)value? "list-inline-item" : "";
					}
					
				});
				
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((ListItemTag)component).getJspBody());
					}
					
				});
				
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean inline;
	
	private JspFragmentVarParser content;
	
	public ListItemTag() {
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		ListTag list = (ListTag) getJspContext().getAttribute(ListTag.PARENT);
		this.inline  = "inline".equals(list.getStyle());
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

	public Boolean getInline() {
		return inline;
	}

	public void setInline(Boolean inline) {
		this.inline = inline;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
