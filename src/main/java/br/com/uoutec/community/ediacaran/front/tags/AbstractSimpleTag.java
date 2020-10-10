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
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.brandao.brutos.bean.BeanInstance;

import br.com.uoutec.community.ediacaran.front.PluginInstaller;
import br.com.uoutec.community.ediacaran.front.TemplateVarParser;
import br.com.uoutec.community.ediacaran.front.tema.Tema;
import br.com.uoutec.community.ediacaran.front.tema.TemaException;
import br.com.uoutec.community.ediacaran.front.tema.TemaRegistry;

public abstract class AbstractSimpleTag extends SimpleTagSupport{

	public static final String WRAPPER_TEMPLATE		= "/bootstrap4/components/wrapper";
	
	public static final String ID_COUNT				= "_component_id_count";

	public static final String ATTR_FORMAT			= "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG			= AbstractSimpleTag.class.getSimpleName() + ":parent";
	
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

	private	String id;

	private String extAttrs;
	
	private String template;
	
	private String classStyle;
	
	private boolean wrapper;
	
    public void doTag() throws JspException, IOException {
    	try {
	    	if(!wrapper)
		    	doInnerTag();
	    	else
	    		doWrapperTag();
    	}
	    catch(TemaException e) {
	    	throw new JspException(e);
	    }
    }
	
    protected void doWrapperTag() throws JspException, IOException {
    	
		Map<String, Object> tagVars = prepareVars();
		Map<String, Object> vars    = new HashMap<String, Object>();
		Writer out                  = getOut();
		String template             = getWrapperTemplate();
		
		vars.putAll(tagVars);
		vars.put("content",	new TemplateVarParser(this.getTemplate() == null? getDefaultTemplate() : getTemplate(), vars));
		
		
		beforeApplyTemplate(template, vars, out);

    	Object oldParent = getParentTag();
    	setParentTag(this);
    	
		applyTemplate(template, vars, out);
    	
		setParentTag(oldParent);
		
		afterApplyTemplate(template, vars, out);
		
    }
    
    protected void doInnerTag() throws JspException, IOException {

    	setProperty(getClass().getName() + ":CONTEXT", this);
    	
		Map<String, Object> vars = prepareVars();
		Writer out               = getOut();
    	String template          = this.getTemplate() == null? getDefaultTemplate() : getTemplate();
    	
		beforeApplyTemplate(template, vars, out);

    	Object oldParent = getParentTag();
    	setParentTag(this);
		
    	applyTemplate(template, vars, out);
    	setParentTag(oldParent);
		afterApplyTemplate(template, vars, out);
		
    	setProperty(getClass().getName() + ":CONTEXT", null);    	
    	
    }
    
    protected Writer getOut() {
    	return getJspContext().getOut();
    }
    
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    protected void applyTemplate(String template, 
    		Map<String,Object> vars, Writer out){
    	
    	PageContext pageContext = (PageContext) getJspContext();
    	TemaRegistry temaRegistry = (TemaRegistry)pageContext.getServletContext().getAttribute(PluginInstaller.TEMA_REGISTRY);
    	Tema tema = temaRegistry.getCurrentTema();
    	String packageName = (String)pageContext.getAttribute(SetTemplatePackageTag.PACKAGE_NAME);
    	packageName = (packageName == null? SetTemplatePackageTag.DEFAULT_PACKAGE_NAME : packageName );
    	tema.applyTagTemplate( "/" + packageName + template, vars, out);
    }
    
    protected void applyTemplate() {
    	
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
		Object old = this.getJspContext().getAttribute(name);
		this.getJspContext().setAttribute(name, newValue);
    	return old;
    }

    protected Object getProperty(String name) {
    	return getProperty(name, null);
    }
    
    protected Object getProperty(String name, Object defaultValue) {
		Object val = this.getJspContext().getAttribute(name);
    	return val == null? defaultValue : val;
    }
    
    protected String getRequestPath() {
    	
    	PageContext pc = (PageContext)super.getJspContext();
    	HttpServletRequest request = (HttpServletRequest)pc.getRequest();
    	
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
    
    protected String parseRequestId(String path, String contextPath){
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
			PageContext pageContext    = (PageContext) getJspContext();  
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
