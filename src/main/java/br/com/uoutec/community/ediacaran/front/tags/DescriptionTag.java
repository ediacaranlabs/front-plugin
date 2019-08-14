package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DescriptionTag  extends AbstractTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/description-list";
	
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
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_PROPS) {{
			add("title");
			add("truncate");
			add("titleWidth");
			add("contentWidth");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_PROPERTY_PARSERS){{
				put("truncate", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return "text-truncate";
					}
					
				});
				
				put("titleWidth", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return value == null? 3 : value;
					}
					
				});
				
				put("contentWidth", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						return value == null? 9 : value;
					}
					
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean truncate;
	
	private Integer titleWidth;
	
	private Integer contentWidth;
	
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
    
	public Map<String, Object> prepareVars() {
		Map<String, Object> vals = super.prepareVars();
		vals.put("content", new JspFragmentVarParser(getJspBody()));
		return vals;
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
	
}
