package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ImageTag  extends AbstractSimpleTag {

	public static final String TEMPLATE  = "/bootstrap4/components/image";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_ATTRS) {{
			add("src");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_PROPS) {{
			add("style");
			add("align");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						if("rounded".equals(value)) {
							return " rounded";
						}
						else
						if("circle".equals(value)) {
							return " rounded-circle";
						}
						else
							return value == null? null : " img-" + value;
					}
					
				});
				
				put("align", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						if("center".equals(value)) {
							return " mx-auto d-block";
						}
						else
							return value == null? null : " float-" + value;
					}
					
				});
				
			}});
	
	/* ------------ Attr ---------------*/
	
	private String src;

	/* ------------ Prop ---------------*/
	
	private String style; //fluid, thumbnail, rounded, circle (img-<...> | rounded | rounded-circle)
	
	private String align; //left, right, center (float-<...> | mx-auto d-block)
	
	public ImageTag() {
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

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}
    
}
