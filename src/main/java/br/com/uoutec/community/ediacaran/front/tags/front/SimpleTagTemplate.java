package br.com.uoutec.community.ediacaran.front.tags.front;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public abstract class SimpleTagTemplate extends AbstractComponent{

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("id");
			add("classStyle");
		}});

	protected static final Set<String> DEFAULT_EMPTY_ATTRIBUTES = 
			Collections.unmodifiableSet(new HashSet<String>());
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
				
				put("classStyle", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return value == null? null : "class";
					}
				});
				
			}});
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("classStyle");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
			}});
	
	public SimpleTagTemplate() throws Throwable {
		super();
	}
	
	public Set<String> getAttributes(){
		return DEFAULT_ATTRS;
	}
	
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
	
}
