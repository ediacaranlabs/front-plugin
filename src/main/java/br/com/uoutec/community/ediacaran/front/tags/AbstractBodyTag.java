package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.brandao.brutos.bean.BeanInstance;

import br.com.uoutec.community.ediacaran.front.TemplateVarParser;
import br.com.uoutec.community.ediacaran.front.TemplatesManagerException;
import br.com.uoutec.community.ediacaran.front.TemplatesManagerProvider;

public abstract class AbstractBodyTag extends BodyTagSupport{

	private static final long serialVersionUID = -6892960845331892542L;

	public static final String WRAPPER_TEMPLATE		= "bootstrap4/components/wrapper";
	
	public static final String ID_COUNT				= "_component_id_count";

	public static final String ATTR_FORMAT			= "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG			= AbstractBodyTag.class.getSimpleName() + ":parent";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("id");
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

	private	String id;

	private String extAttrs;
	
	private String template;
	
	private String classStyle;
	
	private boolean wrapper;
	
    public int doAfterBody() throws JspException {
    	try {
    		if(!wrapper)
	    		doInnerAfterBody();
	    	else
	    		doWrapperAfterBody();
	    	
	    	return SKIP_BODY;
    	}
    	catch(Throwable e) {
    		throw new JspException(e);
    	}
    }
	
    protected void doWrapperAfterBody() throws IOException, TemplatesManagerException{
    	
		Map<String, Object> tagVars = prepareVars();
		Map<String, Object> vars = new HashMap<String, Object>();
		
		vars.putAll(tagVars);
		vars.put("content",	new TemplateVarParser(this.getTemplate() == null? getDefaultTemplate() : getTemplate(), vars));
		
		applyTemplate(getWrapperTemplate(), vars, getBodyContent().getEnclosingWriter());
    }
    
    protected void doInnerAfterBody() throws IOException, TemplatesManagerException {
		Map<String, Object> vars = prepareVars();
		applyTemplate(this.getTemplate() == null? getDefaultTemplate() : getTemplate(), vars, getBodyContent().getEnclosingWriter());
    }
    
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    private void applyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException, TemplatesManagerException {
		
    	Object oldParent = getParentTag();
    	setParentTag(this);
		
		beforeApplyTemplate(template, vars, out);
		
		TemplatesManagerProvider
		.getTemplatesManager().apply(template, vars, out);
		
		afterApplyTemplate(template, vars, out);
		
    	setParentTag(oldParent);
    }
    
    public void setParentTag(Object tag) {
    	setProperty(PARENT_TAG, tag);
    }
    
    public Object getParentTag() {
    	return getProperty(PARENT_TAG);
    }
    
    protected String getWrapperTemplate() {
    	return WRAPPER_TEMPLATE;
    }
    
    protected String getDefaultTemplate() {
    	throw new UnsupportedOperationException();
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

	public String toAttrs() {
		return getAttrList();
	}
	
	private String getAttrList() {
		try {
			StringBuilder sb = new StringBuilder();
			BeanInstance i = new BeanInstance(this);
			Map<String, AttributeParser> parsers = getAttributeParsers();
			Set<String> emptyAttrs = getEmptyAttributes();
			
			for(String p: getDefaultAttributes()) {
				
				Object v = i.get(p);
				
				if(v == null || emptyAttrs.contains(p)) {
					continue;
				}
				
				if(sb.length() != 0) {
					sb.append(" ");
				}
				
				AttributeParser parser = parsers.get(p);
				
				p = parser == null? p : parser.toName(p, this);
				v = parser == null? v : parser.toValue(v, this);
				
				if(p != null) {
					sb.append(p).append("=\"").append(v).append("\"");
				}
				else {
					sb.append(v);
				}
				
			}
			
			if(this.extAttrs != null) {
				
				if(sb.length() != 0) {
					sb.append(" ");
				}
				
				sb.append(this.extAttrs);
			}
			
			return sb.toString();
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}
	
	protected void beforePrepareVars(Map<String, Object> vars) {
	}
	
	protected void afterPrepareVars(Map<String, Object> vars) {
	}
	
	protected Map<String, Object> prepareVars() {
		try {
			Map<String, AttributeParser> parsers = getPropertyParsers();
			Map<String, Object> map              = new HashMap<String, Object>();
			BeanInstance i                       = new BeanInstance(this);
			
			this.beforePrepareVars(map);
			
			map.put("attr", this.getAttrList());
			
			for(String k: getDefaultProperties()) {
				
				Object v = i.get(k);
				
				AttributeParser parser = parsers.get(k);
				
				k = parser == null? k : parser.toName(k, this);
				v = parser == null? v : parser.toValue(v, this);
				
				if(k != null && !k.isEmpty()) {
					map.put(k, v);
				}
			}
			
			this.afterPrepareVars(map);
			
			return map;
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}
	
    protected Object setProperty(String name, Object newValue) {
		Object old = pageContext.getAttribute(name);
		pageContext.setAttribute(name, newValue);
    	return old;
    }

    protected Object getProperty(String name) {
    	return getProperty(name, null);
    }
    
    protected Object getProperty(String name, Object defaultValue) {
		Object val = pageContext.getAttribute(name);
    	return val == null? defaultValue : val;
    }
    
	public String getExtAttrs() {
		return extAttrs;
	}

	public void setExtAttrs(String extAttrs) {
		this.extAttrs = extAttrs;
	}

	public String getId() {
		if(id == null) {
			Integer acc = (Integer) pageContext.getAttribute(ID_COUNT);
			pageContext.setAttribute(ID_COUNT, acc = acc == null? 0 : acc.intValue() + 1);
			return id = String.valueOf(acc);
		}
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean isWrapper() {
		return wrapper;
	}

	public void setWrapper(boolean wrapper) {
		this.wrapper = wrapper;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}
    
}
