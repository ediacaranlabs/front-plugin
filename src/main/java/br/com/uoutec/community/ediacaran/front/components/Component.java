package br.com.uoutec.community.ediacaran.front.components;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.community.ediacaran.front.tags.SetTemplatePackageTag;
import br.com.uoutec.community.ediacaran.front.theme.ComponentVars;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;
import br.com.uoutec.community.ediacaran.front.theme.ThemeRegistry;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;

public class Component 
	implements ComponentVars {

	public static final String WRAPPER_TEMPLATE		= "/components/wrapper";
	
	public static final String ID_COUNT				= "_component_id_count";

	public static final String ATTR_FORMAT			= "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG			= "Component:parent";

	private PageContext pageContext;
	
	private Writer out;
	
	private ComponentData componentData;
	
	public void build() throws ThemeException, IOException{
    	if(!componentData.isWrapper()) {
    		applySimpleTemplate();
    	}
    	else {
    		applyWrapperTemplate();
    	}
    }
    
    protected void applyWrapperTemplate() throws IOException{
    	
		Map<String, Object> vars = new HashMap<String, Object>();
		Writer out               = getOut();
		String template          = componentData.getWrapperTemplate();
    	String contentTemplate   = componentData.getTemplate() == null? getDefaultTemplate() : componentData.getTemplate();
    	Theme theme              = getTheme();
    	String packageName       = getPackageTheme();
		
		vars.put("content",	new TemplateVarParser(contentTemplate, packageName, this, theme));
		
		beforeApplyTemplate(template, vars, out);

    	//Object oldParent = getParentTag();
    	//setParentTag(componentData);
    	
		applyTemplate(template, vars, out);
    	
		//setParentTag(oldParent);
		
		afterApplyTemplate(template, vars, out);
		
    }

    protected void applySimpleTemplate() throws IOException {

    	setProperty(componentData.getClass().getName() + ":CONTEXT", componentData);
    	
		Map<String, Object> vars = new HashMap<String, Object>();
    	Writer out               = getOut();
    	String template          = componentData.getTemplate() == null? getDefaultTemplate() : componentData.getTemplate();
    	
		beforeApplyTemplate(template, vars, out);

    	//Object oldParent = getParentTag();
    	//setParentTag(componentData);
		
    	applyTemplate(template, vars, out);
    	
    	//setParentTag(oldParent);
		afterApplyTemplate(template, vars, out);
		
    	setProperty(componentData.getClass().getName() + ":CONTEXT", null);    	
    	
    }
    
    public String getDefaultTemplate() {
		return componentData.getDefaultTemplate();
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
		return componentData;
	}

	public void setComponentData(ComponentData value) {
		this.componentData = value;
	}

	protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    }
    
    protected void applyTemplate(String template, Map<String, Object> vars, Writer out){
    	getTheme().buildComponent(template, getPackageTheme(), this, vars, out);
    }
    
    //public void setParentTag(Object tag) {
    //	setProperty(PARENT_TAG, tag);
    //}
    
    //public Object getParentTag() {
    //	return getProperty(PARENT_TAG);
    //}
    
	protected void beforePrepareVars(Map<String, Object> vars) {
	}
	
	protected void afterPrepareVars(Map<String, Object> vars) {
	}
	
	private String getAttrList(Map<String, PropertyParser> attributeParsers, 
			Set<String> emptyAttributes, Set<String> defaultAttributes) {
		
		Map<String,Object> vars = getVars(defaultAttributes, emptyAttributes);
		StringBuilder sb = new StringBuilder();
		PropertiesComponentTemplate cp = new PropertiesComponentTemplateImp(this, componentData.getType(), vars);
		
		for(Entry<String,Object> e: vars.entrySet()) {
			
			String p = e.getKey();
			Object v = e.getValue(); 
			
			
			if(v == null || emptyAttributes.contains(p)) {
				continue;
			}
			
			if(sb.length() != 0) {
				sb.append(" ");
			}
			
			PropertyParser parser = attributeParsers.get(p);
			
			if(parser != null) {
				p = parser.toName(p, cp);
				v = parser.toValue(v, cp);
			}

			if(v != null){
				
				if(p != null && !p.isEmpty()) {
					sb.append(p).append("=\"").append(v).append("\"");
				}
				else{
					sb.append(v);
				}
				
			}

		}
		
		if(componentData.getExtAttrs() != null) {
			
			if(sb.length() != 0) {
				sb.append(" ");
			}
			
			sb.append(componentData.getExtAttrs());
		}
		
		return sb.toString();
		
	}

	public Map<String, Object> prepareVars(Map<String, PropertyParser> propertyParsers, Set<String> defaultProperties,
			Map<String, PropertyParser> attributeParsers, Set<String> emptyAttributes, Set<String> defaultAttributes) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		this.beforePrepareVars(result);
		
		Map<String,Object> vars = getVars(defaultProperties, null);
		PropertiesComponentTemplate cp = new PropertiesComponentTemplateImp(this, componentData.getType(), vars);

		result.put("attr", getAttrList(attributeParsers, emptyAttributes, defaultAttributes));
		
		for(Entry<String,Object> e: vars.entrySet()) {
			
			String p = e.getKey();
			Object v = e.getValue(); 
			
			PropertyParser parser = propertyParsers.get(p);
			
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
		return componentData.getProperties(defaultProperties, emptyProperties);
	}
	
	public Theme getTheme() {
		return ContextSystemSecurityCheck.doPrivileged(()->{
			ThemeRegistry themeRegistry = EntityContextPlugin.getEntity(ThemeRegistry.class);
	    	return themeRegistry.getCurrentTheme();
		});
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
		
		Long acc = (Long) request.getAttribute(ID_COUNT);
		request.setAttribute(ID_COUNT, acc = acc == null? System.currentTimeMillis() : acc.intValue() + 1);
		
		return componentData.getClass().getSimpleName().toLowerCase() + "_" + Long.toHexString(acc);
	}

}
