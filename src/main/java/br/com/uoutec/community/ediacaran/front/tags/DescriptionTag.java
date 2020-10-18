package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class DescriptionTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/bootstrap4/components/description";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(ComponentFormTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(ComponentFormTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleComponent.DEFAULT_PROPS) {{
			add("title");
			add("truncate");
			add("titleWidth");
			add("contentWidth");
			add("content");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleComponent.DEFAULT_PROPERTY_PARSERS){{
				put("truncate", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return "text-truncate";
					}
					
				});
				
				put("titleWidth", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? 3 : value;
					}
					
				});
				
				put("contentWidth", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return value == null? 9 : value;
					}
					
				});
				
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((DescriptionTag)component).getJspBody());
					}
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean truncate;
	
	private Integer titleWidth;
	
	private Integer contentWidth;
	
	private JspFragmentVarParser content;
	
	public DescriptionTag() {
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
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getTruncate() {
		return truncate;
	}

	public void setTruncate(Boolean truncate) {
		this.truncate = truncate;
	}

	public Integer getTitleWidth() {
		return titleWidth;
	}

	public void setTitleWidth(Integer titleWidth) {
		this.titleWidth = titleWidth;
	}

	public Integer getContentWidth() {
		return contentWidth;
	}

	public void setContentWidth(Integer contentWidth) {
		this.contentWidth = contentWidth;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
	
}
