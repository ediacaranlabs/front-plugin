package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.StringPattern;

public abstract class ComponentFormTag extends AbstractTag {

	public static final String TEMPLATE = "bootstrap4/templates/components/form-control";
	
	public static final String FORM = ComponentFormTag.class.getSimpleName() + ":form";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
			add("name");
			add("value");
			add("enabled");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("enabled", new AttributeParserImp() {
				
				@Override
				public Object toValue(Object value) {
					return value != null && !(Boolean)value? "disabled" : "";
				}
			});
			
		}});
	
	/* ------------ Attr ---------------*/
	
	private String name;

	private String value;
	
	private Boolean enabled;

	public ComponentFormTag() {
	}
	
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }
    
    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }
	
    public void doTag() throws JspException, IOException {
		
    	if(getProperty(FORM) == null) {
    		super.doTag();
    		return;
    	}
		
		Object oldParent = getProperty(PARENT_TAG);

		setProperty(PARENT_TAG, this);
		
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("attr",    super.toAttrs());
			vars.put("component", new StringPattern.AbstractVarParser() {
				
				@Override
				public void parse(Writer writter) throws IOException {
					try {
						doInnerTag();
					} catch (JspException e) {
						throw new IOException(e);
					}
				}
				
			});
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
		
    	doInnerTag();
    	setProperty(PARENT_TAG, oldParent);
    	
    }
	
	public String toAttrs() {
		return getProperty(FORM) == null? super.toAttrs() : "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
