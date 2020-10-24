package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.brandao.brutos.bean.BeanInstance;

import br.com.uoutec.community.ediacaran.system.Constants;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.ComponentVars;
import br.com.uoutec.community.ediacaran.system.tema.Theme;
import br.com.uoutec.community.ediacaran.system.tema.ThemeException;
import br.com.uoutec.community.ediacaran.system.tema.TemaRegistry;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;

public abstract class AbstractPanelComponent 
	extends BodyTagSupport
	implements ComponentVars{

	private static final long serialVersionUID = -5353589232919296817L;

	public static final String WRAPPER_TEMPLATE		= "/components/wrapper";
	
	public static final String ID_COUNT				= "_component_id_count";

	public static final String ATTR_FORMAT			= "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG			= AbstractPanelComponent.class.getSimpleName() + ":parent";
	
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
	
	private Object oldParent;
	
    public void doInitBody() throws JspException {
    	setProperty(getClass().getName() + ":CONTEXT", this);
    	
    	oldParent = getParentTag();
    	setParentTag(this);
    }
	
    public int doAfterBody() throws JspException {
    	try {
    		applyTemplate();
        	setParentTag(oldParent);
        	setProperty(getClass().getName() + ":CONTEXT", null);    	
        	return SKIP_BODY;
    	}
	    catch(ThemeException e) {
	    	throw new JspException(e);
	    } 
    	catch (IOException e) {
	    	throw new JspException(e);
		}
    	
    }

    protected void applyTemplate() throws ThemeException, IOException{
    	if(!wrapper) {
    		applySimpleTemplate();
    	}
    	else {
    		applyWrapperTemplate();
    	}
    }
    
    protected void applyWrapperTemplate() throws IOException{
    	
		Map<String, Object> vars = new HashMap<String, Object>();
		Writer out               = getBodyContent().getEnclosingWriter();
		String template          = getWrapperTemplate();
    	Theme tema               = getTheme();
    	String packageName       = getPackageTheme();
		
		vars.put("content",	new TemplateVarParser(getTemplate() == null? getDefaultTemplate() : getTemplate(), packageName, this, tema));
		
		beforeApplyTemplate(template, vars, out);

    	Object oldParent = getParentTag();
    	setParentTag(this);
    	
		applyTemplate(template, vars, out);
    	
		setParentTag(oldParent);
		
		afterApplyTemplate(template, vars, out);
		
    }

    protected void applySimpleTemplate() throws IOException {

    	setProperty(getClass().getName() + ":CONTEXT", this);
    	
    	Writer out               = getBodyContent().getEnclosingWriter();
    	String template          = getTemplate() == null? getDefaultTemplate() : getTemplate();
    	
		beforeApplyTemplate(template, null, out);

    	Object oldParent = getParentTag();
    	setParentTag(this);
		
    	applyTemplate(template, null, out);
    	
    	setParentTag(oldParent);
		afterApplyTemplate(template, null, out);
		
    	setProperty(getClass().getName() + ":CONTEXT", null);    	
    	
    }
    
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    protected void applyTemplate(String template, Map<String, Object> vars, Writer out){
    	getTheme().applyTagTemplate(template, getPackageTheme(), this, vars, out);
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
	
	protected void beforePrepareVars(Map<String, Object> vars) {
	}
	
	protected void afterPrepareVars(Map<String, Object> vars) {
	}
	
	private String getAttrList(Map<String, AttributeParser> attributeParsers, 
			Set<String> emptyAttributes, Set<String> defaultAttributes) {
		try {
			StringBuilder sb = new StringBuilder();
			BeanInstance i = new BeanInstance(this);
			
			for(String p: defaultAttributes) {
				
				Object v = i.get(p);
				
				if(v == null || emptyAttributes.contains(p)) {
					continue;
				}
				
				if(sb.length() != 0) {
					sb.append(" ");
				}
				
				AttributeParser parser = attributeParsers.get(p);
				
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
	
	public Map<String, Object> prepareVars(Map<String, AttributeParser> propertyParsers, Set<String> defaultProperties,
			Map<String, AttributeParser> attributeParsers, Set<String> emptyAttributes, Set<String> defaultAttributes) {
		try {
			Map<String, Object> map              = new HashMap<String, Object>();
			BeanInstance i                       = new BeanInstance(this);
			
			this.beforePrepareVars(map);
			
			map.put("attr", getAttrList(attributeParsers, emptyAttributes, defaultAttributes));
			
			for(String k: defaultProperties) {
				
				Object v = i.get(k);
				
				AttributeParser parser = propertyParsers.get(k);
				
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
	
	protected Theme getTheme() {
    	TemaRegistry temaRegistry = (TemaRegistry)getProperty(Constants.THEME_REGISTRY);
    	return temaRegistry.getCurrentTema();
	}
	
	protected String getPackageTheme() {
    	return (String)getProperty(SetTemplatePackageTag.PACKAGE_NAME);
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
    
    protected String getRequestPath() {
    	
    	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    	
		String include = (String) request.getAttribute("javax.servlet.include.request_uri");
		
		if(include != null) {
			return parseRequestId(
					include, 
					(String)request.getAttribute("javax.servlet.include.context_path"));
		}
		else {
			return parseRequestId(
					request.getRequestURI(), 
					request.getContextPath());
		}
    	
    }
    
    private String parseRequestId(String path, String contextPath){
        return path.substring( contextPath.length(), path.length() );
    }
    
	public String getExtAttrs() {
		return extAttrs;
	}

	public void setExtAttrs(String extAttrs) {
		this.extAttrs = extAttrs;
	}

	public String getId() {
		
		if(id == null) {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			
			Integer acc = (Integer) request.getAttribute(ID_COUNT);
			request.setAttribute(ID_COUNT, acc = acc == null? 0 : acc.intValue() + 1);
			
			return id = getClass().getSimpleName().toLowerCase() + String.valueOf(acc);
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