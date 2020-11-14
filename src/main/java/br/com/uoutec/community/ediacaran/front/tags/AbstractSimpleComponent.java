package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.brandao.brutos.bean.BeanInstance;

import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.ComponentVars;
import br.com.uoutec.community.ediacaran.system.tema.TagTemplate.VarParser;
import br.com.uoutec.community.ediacaran.system.tema.TemplateVarParser;
import br.com.uoutec.community.ediacaran.system.tema.Theme;
import br.com.uoutec.community.ediacaran.system.tema.ThemeException;
import br.com.uoutec.community.ediacaran.system.tema.ThemeRegistry;

public abstract class AbstractSimpleComponent 
	extends SimpleTagSupport 
	implements ComponentVars{

	public static final String WRAPPER_TEMPLATE		= "/components/wrapper";
	
	public static final String ID_COUNT				= "_component_id_count";

	public static final String ATTR_FORMAT			= "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG			= "Component:parent";

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
	    catch(ThemeException e) {
	    	throw new JspException(e);
	    }
    }
	
    protected void doWrapperTag() throws JspException, IOException {
    	
		Map<String, Object> vars    = new HashMap<String, Object>();
		Writer out                  = getOut();
		String template             = getWrapperTemplate();
    	Theme tema                   = getTheme();
    	String packageName          = getTemaPackage();
		
		vars.put("content",	new TemplateVarParser(getTemplate() == null? getDefaultTemplate() : getTemplate(), packageName, this, tema));
		
		beforeApplyTemplate(template, vars, out);

    	Object oldParent = getParentTag();
    	setParentTag(this);
    	
		applyTemplate(template, vars, out);
    	
		setParentTag(oldParent);
		
		afterApplyTemplate(template, vars, out);
		
    }
    
    protected void doInnerTag() throws JspException, IOException {

    	setProperty(getClass().getName() + ":CONTEXT", this);
    	
		Map<String, Object> vars = new HashMap<String, Object>();
		Writer out               = getOut();
    	String template          = getTemplate() == null? getDefaultTemplate() : getTemplate();
    	
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
    
    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
    }
    
    protected void afterApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
    }
    
    protected void applyTemplate(String template, Map<String, Object> vars, Writer out){
    	getTheme().applyTagTemplate(template, getTemaPackage(), this, vars, out);
    }
    
	public Theme getTheme() {
    	//ThemeRegistry temaRegistry = (ThemeRegistry)getProperty(Constants.THEME_REGISTRY);
		ThemeRegistry temaRegistry = EntityContextPlugin.getEntity(ThemeRegistry.class);
    	return temaRegistry.getCurrentTheme();
	}
	
	public String getTemaPackage() {
    	return (String)getProperty(SetTemplatePackageTag.PACKAGE_NAME);
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
    
    protected abstract String getDefaultTemplate();
    
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
	
	protected void beforePrepareVars(Map<String, Object> vars) {
	}
	
	protected void afterPrepareVars(Map<String, Object> vars) {
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

	public VarParser toVarParser() {
		return new VarParser() {
			
			@Override
			public String parse() throws IOException {
				return null;
			}
			
			@Override
			public void parse(Writer writter) throws IOException {
				try {
					JspFragment body = getJspBody();
					if(body != null) {
						body.invoke(writter);
					}
				}
				catch(Throwable e) {
					throw new IllegalStateException(e);
				}
			}
		};
	}
	/*
	public void initTag(SimpleTagSupport tag) {
		tag.setJspBody(super.getJspBody());
		tag.setJspContext(super.getJspContext());
		tag.setParent(this);
	}
	
	public void initTag(BodyTagSupport tag) {
		PageContext pageContext = (PageContext) getJspContext();
		tag.setBodyContent(new BodyContentImpl(new JspWriterImpl(pageContext.getResponse())));
		tag.setPageContext(pageContext);
	}
	*/
	
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
