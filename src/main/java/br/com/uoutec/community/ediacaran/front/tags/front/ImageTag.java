package br.com.uoutec.community.ediacaran.front.tags.front;

import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.TemaException;

public class ImageTag extends AbstractTagTemplate {

	public static final String TEMPLATE  = "/components/image";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTagTemplate.DEFAULT_ATTRS) {{
			add("src");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTagTemplate.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTagTemplate.DEFAULT_PROPS) {{
			add("style");
			add("align");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTagTemplate.DEFAULT_PROPERTY_PARSERS){{
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

    public Set<String> getEmptyAttributes(){
    	return DEFAULT_EMPTY_ATTRIBUTES;
    }
    
    public Map<String, AttributeParser> getAttributesParser(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

    public Set<String> getProperties(){
    	return DEFAULT_PROPS;
    }

    public Map<String, AttributeParser> getPropertiesParse(){
    	return DEFAULT_PROPERTY_PARSERS;
    }

	@Override
	public void applyTagTemplate(Map<String, Object> vars, Writer out) throws TemaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyTagTemplate(Writer out, Object... vars) throws TemaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
