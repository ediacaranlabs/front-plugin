package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class ImageTag  extends AbstractTag {

	public static final String TEMPLATE  = "/bootstrap4/templates/components/image";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
			//add("");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
			/*
			put("", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value;
				}
				
				@Override
				public Object toValue(Object value) {
					return value;
				}
				
			});
			*/
			
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("style");
			add("align");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						if("rounded".equals(value)) {
							return " rounded";
						}
						else
						if("circle".equals(value)) {
							return " rounded-circle";
						}
						else
							return " img-" + value;
					}
					
				});
				
				put("align", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value) {
						if("center".equals(value)) {
							return " mx-auto d-block";
						}
						else
							return " float-" + value;
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
	
    public void doInnerTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = super.getValues();
			
			TemplatesManager.getTemplatesManager()
				.apply(
						this.getTemplate() == null? 
								TEMPLATE : 
								this.getTemplate(), 
						vars, getJspContext().getOut()
				);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
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
