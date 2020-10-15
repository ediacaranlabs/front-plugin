package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class IconTag extends AbstractSimpleTag {

	public static final String TEMPLATE       = "/bootstrap4/components/icon";
	
	public static final String TEMPLATE_STACK = "/bootstrap4/components/icon-stack";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_PROPS) {{
			add("size");
			add("bgSize");
			add("iconSize");
			add("bg");
			add("icon");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{

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
				
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer size;
	
	private Integer bgSize;
	
	private Integer iconSize;
	
	private String bg;
	
	private String icon;
	
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
