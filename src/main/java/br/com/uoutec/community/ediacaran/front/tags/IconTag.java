package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IconTag extends AbstractTag {

	public static final String TEMPLATE       = "/bootstrap4/templates/components/icon";
	
	public static final String TEMPLATE_STACK = "/bootstrap4/templates/components/icon-stack";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_PROPS) {{
			add("size");
			add("bgSize");
			add("iconSize");
			add("bg");
			add("name");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_PROPERTY_PARSERS){{

				put("bgSize", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "bg-size";
					}
					
				});
				
				put("iconSize", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "icon-size";
					}
					
				});
				
				put("icon", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "name";
					}
					
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer size;
	
	private Integer bgSize;
	
	private Integer iconSize;
	
	private String bg;
	
	private String name;
	
	public IconTag() {
	}

    protected String getDefaultTemplate() {
    	return bg != null? TEMPLATE_STACK : TEMPLATE;
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
		return size == null? 3 : size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getBgSize() {
		return bgSize == null? getSize() - 1 : bgSize;
	}

	public void setBgSize(Integer bgSize) {
		this.bgSize = bgSize;
	}

	public Integer getIconSize() {
		return iconSize == null? getBgSize() - 1 : iconSize;
	}

	public void setIconSize(Integer iconSize) {
		this.iconSize = iconSize;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
