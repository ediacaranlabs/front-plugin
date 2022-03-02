package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.brandao.brutos.bean.BeanInstance;

import br.com.uoutec.community.ediacaran.DoPrivilegedException;
import br.com.uoutec.community.ediacaran.front.theme.AttributeParser;
import br.com.uoutec.community.ediacaran.front.theme.ComponentVars;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;
import br.com.uoutec.community.ediacaran.front.theme.ThemeRegistry;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

public class TagComponent 
	implements ComponentVars {

	public static final String WRAPPER_TEMPLATE		= "/components/wrapper";
	
	public static final String ID_COUNT				= "_component_id_count";

	public static final String ATTR_FORMAT			= "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG			= "Component:parent";

	private PageContext pageContext;
	
	private Writer out;
	
	private ComponentInfo componentInfo;
	
	public void applyTemplate() throws ThemeException, IOException{
    	if(!componentInfo.isWrapper()) {
    		applySimpleTemplate();
    	}
    	else {
    		applyWrapperTemplate();
    	}
    }
    
    protected void applyWrapperTemplate() throws IOException{
    	
		Map<String, Object> vars = new HashMap<String, Object>();
		Writer out               = getOut();
		String template          = getWrapperTemplate();
    	Theme theme              = getTheme();
    	String packageName       = getPackageTheme();
		
		vars.put("content",	new TemplateVarParser(componentInfo.getTemplate() == null? getDefaultTemplate() : componentInfo.getTemplate(), packageName, this, theme));
		
		beforeApplyTemplate(template, vars, out);

    	Object oldParent = getParentTag();
    	setParentTag(componentInfo);
    	
		applyTemplate(template, vars, out);
    	
		setParentTag(oldParent);
		
		afterApplyTemplate(template, vars, out);
		
    }

    protected void applySimpleTemplate() throws IOException {

    	setProperty(getClass().getName() + ":CONTEXT", componentInfo);
    	
		Map<String, Object> vars = new HashMap<String, Object>();
    	Writer out               = getOut();
    	String template          = componentInfo.getTemplate() == null? getDefaultTemplate() : componentInfo.getTemplate();
    	
		beforeApplyTemplate(template, vars, out);

    	Object oldParent = getParentTag();
    	setParentTag(componentInfo);
		
    	applyTemplate(template, vars, out);
    	
    	setParentTag(oldParent);
		afterApplyTemplate(template, vars, out);
		
    	setProperty(getClass().getName() + ":CONTEXT", null);    	
    	
    }
    
    public String getDefaultTemplate() {
		return componentInfo.getDefaultTemplate();
	}

	public PageContext getPageContext() {
		return pageContext;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public Writer getOut() {
		return out;
	}

	public void setOut(Writer out) {
		this.out = out;
	}

	public Object getComponentInfo() {
		return componentInfo;
	}

	public void setComponentInfo(ComponentInfo value) {
		this.componentInfo = value;
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
	
	protected void beforePrepareVars(Map<String, Object> vars) {
	}
	
	protected void afterPrepareVars(Map<String, Object> vars) {
	}
	
	private String getAttrList(Map<String, AttributeParser> attributeParsers, 
			Set<String> emptyAttributes, Set<String> defaultAttributes) {
		
		Map<String,Object> vars = getVars(defaultAttributes, emptyAttributes);
		StringBuilder sb = new StringBuilder();
		ComponentProperties cp = new ComponentPropertiesImp(this, vars);
		
		for(Entry<String,Object> e: vars.entrySet()) {
			
			String p = e.getKey();
			Object v = e.getValue(); 
			
			
			if(v == null || emptyAttributes.contains(p)) {
				continue;
			}
			
			if(sb.length() != 0) {
				sb.append(" ");
			}
			
			AttributeParser parser = attributeParsers.get(p);
			
			if(parser != null) {
				p = parser.toName(p, cp);
				v = parser.toValue(v, cp);
			}
			
			if(p != null) {
				sb.append(p).append("=\"").append(v).append("\"");
			}
			else {
				sb.append(v);
			}
			
		}
		
		if(componentInfo.getExtAttrs() != null) {
			
			if(sb.length() != 0) {
				sb.append(" ");
			}
			
			sb.append(componentInfo.getExtAttrs());
		}
		
		return sb.toString();
		
	}
	
	public Map<String, Object> prepareVars(Map<String, AttributeParser> propertyParsers, Set<String> defaultProperties,
			Map<String, AttributeParser> attributeParsers, Set<String> emptyAttributes, Set<String> defaultAttributes) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		this.beforePrepareVars(result);
		
		Map<String,Object> vars = getVars(defaultProperties, null);
		ComponentProperties cp = new ComponentPropertiesImp(this, vars);

		result.put("attr", getAttrList(attributeParsers, emptyAttributes, defaultAttributes));
		
		for(Entry<String,Object> e: vars.entrySet()) {
			
			String p = e.getKey();
			Object v = e.getValue(); 
			
			AttributeParser parser = propertyParsers.get(p);
			
			if(parser != null) {
				p = parser.toName(p, cp);
				v = parser.toValue(v, cp);
			}
			
			if(p != null && !p.isEmpty()) {
				result.put(p, v);
			}
			
		}
		
		/*
		if(result.get("id") == null) {
			result.put("id", getId());
		}
		*/
		
		this.afterPrepareVars(result);
		
		return result;
	}
	
	private Map<String,Object> getVars(Set<String> defaultProperties, Set<String> emptyProperties){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		BeanInstance i = 
			AccessController.doPrivileged(new PrivilegedAction<BeanInstance>() {
            public BeanInstance run() {
                return new BeanInstance(componentInfo);
            }
        });

		for(String p: defaultProperties) {
			final String k = p;
			
			Object v = 
					AccessController.doPrivileged(new PrivilegedAction<Object>() {
		            public Object run() {
		                try {
							return i.get(k);
						} catch (IllegalAccessException e) {
							throw new DoPrivilegedException(e);
						} catch (IllegalArgumentException e) {
							throw new DoPrivilegedException(e);
						} catch (InvocationTargetException e) {
							throw new DoPrivilegedException(e);
						}
		            }
		        });
			
			
			if(emptyProperties != null && emptyProperties.contains(p)) {
				continue;
			}
			
			map.put(p, v);
		}
		
		return map;
	}
	
	public Theme getTheme() {
		ThemeRegistry themeRegistry = EntityContextPlugin.getEntity(ThemeRegistry.class);
    	return themeRegistry.getCurrentTheme();
	}
	
	public String getPackageTheme() {
    	return (String)getProperty(SetTemplatePackageTag.PACKAGE_NAME);
	}
	
    public Object setProperty(String name, Object newValue) {
		Object old = pageContext.getAttribute(name);
		pageContext.setAttribute(name, newValue);
    	return old;
    }

    public Object getProperty(String name) {
    	return getProperty(name, null);
    }
    
    public Object getProperty(String name, Object defaultValue) {
		Object val = pageContext.getAttribute(name);
    	return val == null? defaultValue : val;
    }
    
    public String getRequestPath() {
    	
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
    
	public String getId() {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		Integer acc = (Integer) request.getAttribute(ID_COUNT);
		request.setAttribute(ID_COUNT, acc = acc == null? 0 : acc.intValue() + 1);
		
		return componentInfo.getClass().getSimpleName().toLowerCase() + String.valueOf(acc);
	}

}
