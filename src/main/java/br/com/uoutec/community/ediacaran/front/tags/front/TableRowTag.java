package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class TableRowTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/bootstrap4/components/table-row-header";
	
	public static final String TEMPLATE_2  = "/bootstrap4/components/table-row";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleComponent.DEFAULT_ATTRS) {{
			add("size");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_ATTRIBUTE_PARSERS){{
			put("size", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return "rowspan";
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value != null && (Integer)value > 1 ? (Integer)value - 1: "";
				}
			});
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleComponent.DEFAULT_PROPS) {{
			add("content");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((TableRowTag)component).getJspBody());
					}
					
				});
			}});
	
	/* ------------ Attr ---------------*/
	
	private Integer size;
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	public TableRowTag() {
	}
	
    protected String getDefaultTemplate() {
    	Object parent = this.getParentTag();
    	return parent instanceof TableHeaderTag? TEMPLATE : TEMPLATE_2;
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
