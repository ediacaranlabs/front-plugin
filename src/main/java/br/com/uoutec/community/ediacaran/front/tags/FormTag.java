package br.com.uoutec.community.ediacaran.front.tags;

import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;

public class FormTag extends AbstractSimpleTag {

	public static final String TEMPLATE = "/bootstrap4/components/form";
	
	public static final String FORM = FormTag.class.getSimpleName() + ":form";
	
	public static final String VERTICAL_FORM = FormTag.class.getSimpleName() + ":vertical_form";
	
	public static final String VERTICAL_FORM_VALUE = "true";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_ATTRS) {{
			add("acceptCharset");
			add("action");
			add("enctype");
			add("method");
			add("target");
			add("update");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_ATTRIBUTE_PARSERS){{
			
			put("acceptCharset", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return value == null? null : "accept-charset";
				}
			});

			put("update", new AttributeParserImp() {
				
				@Override
				public String toName(String value, Object component) {
					return "dest-content";
				}
				
				@Override
				public Object toValue(Object value, Object component) {
					return value;
				}
			});
			
		}});
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_PROPS) {{
			add("content");
			add("style");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
				put("content", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return "form-body";
					}
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((FormTag)component).getJspBody());
					}
				});
				
				put("style", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return "inline".equals(value)? "form-inline " : "";
					}
				});
				
				
			}});
	
	/* ------------ Attr ---------------*/
	
	private String style; // inline, horizontal, vertical
	
	private String acceptCharset;
	
	private String action;
	
	private String enctype;
	
	private String method;
	
	private String target;
	
	private String update;
	
	/* ------------ Prop ---------------*/

	private JspFragmentVarParser content;
	
	public FormTag() {
	}

    protected void applyTemplate(String template, 
    		Map<String,Object> vars, Writer out){
    	
    	Object oldForm = super.setProperty(FORM, this);
    	/*
    	if("vertical".equals(this.style)) {
	    	Object old = super.setProperty(VERTICAL_FORM, VERTICAL_FORM_VALUE);
	    	super.applyTemplate(template, vars, out);
	    	super.setProperty(VERTICAL_FORM, old);
    	}
    	else {
	    	super.applyTemplate(template, vars, out);
    	}
    	*/
    	
    	super.applyTemplate(template, vars, out);
    	super.setProperty(FORM, oldForm);
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

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}
	
}
