package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public abstract class FormTag extends AbstractTag {

	public static final String TEMPLATE = "bootstrap4/templates/components/form";
	
	public static final String FORM = FormTag.class.getSimpleName() + ":form";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractTag.DEFAULT_ATTRS) {{
			add("acceptCharset");
			add("action");
			add("enctype");
			add("method");
			add("target");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("acceptCharset", new AttributeParserImp() {
				
				@Override
				public String toName(String value) {
					return value == null? null : "accept-charset";
				}
			});

		}});
	
	private String acceptCharset;
	
	private String action;
	
	private String enctype;
	
	private String method;
	
	private String target;
	
	public FormTag() {
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
	
	@Override
	public void doTag() throws JspException, IOException {
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr",      super.toAttrs());
			vars.put("form-body", new JspFragmentVarParser(getJspBody()));
			
			Object old = setProperty(FORM, this);
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
			
			setProperty(FORM, old);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

	public String getAcceptCharset() {
		return acceptCharset;
	}

	public void setAcceptCharset(String acceptCharset) {
		this.acceptCharset = acceptCharset;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEnctype() {
		return enctype;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
    
}
