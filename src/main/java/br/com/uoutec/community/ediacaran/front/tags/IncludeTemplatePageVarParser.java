package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.PageContextVarParser;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.PropertiesVarParser;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

public class IncludeTemplatePageVarParser 
	implements VarParser, 
	PageContextVarParser,
	PropertiesVarParser{

	private HttpServletResponse response;
	
	private HttpServletRequest request;
	
	@SuppressWarnings("unused")
	private PageContext pageContext;
	
	private String jspTemplate;

	private Map<String, Object> properties;
	
	public IncludeTemplatePageVarParser(String jspTemplate) {
		this.jspTemplate = jspTemplate;
	}
	
	protected String getJSPTemplate() {
		return jspTemplate;
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		
		String localJSPTemplate = getJSPTemplate();
		String localContext     = null;
		
		if(localJSPTemplate.contains(":")) {
			String[] tmp = localJSPTemplate.split(localContext, 2);
			localContext = tmp[0];
			localJSPTemplate = tmp[1];
		}
		
    	ServletContext servletContext = request.getServletContext();
    	
    	String currentContext 	= servletContext.getContextPath().toLowerCase();
	    	
    	if(localContext != null && !currentContext.equals(localContext)) {
    		servletContext = servletContext.getContext(localContext);
    	}
    	
    	ImportResponseWrapper irw = new ImportResponseWrapper(response, writter); 
    	
    	try {
    		boolean newContext = !servletContext.equals(request.getServletContext());
    		
    		request.setAttribute("newContext", newContext);
    		request.setAttribute("vars", properties);
    		
    		servletContext.getRequestDispatcher(localJSPTemplate).include(request, irw);
    	}
    	catch(ServletException e) {
    		throw new IOException("unable to load page " + localJSPTemplate, e.getCause());
    	}
    	
    	if (irw.getStatus() < 200 || irw.getStatus() > 299) { 
            throw new IOException("unable to load page " + localJSPTemplate + " [status=" + irw.getStatus() + "]"); 
        }		
	}

	@Override
	public String parse() throws IOException {
		return null;
	}

	@Override
	public void setPageContext(PageContext value) {
		this.pageContext = value;
		this.request = (HttpServletRequest) value.getRequest();
		this.response = (HttpServletResponse) value.getResponse();
	}

	@Override
	public void setProperties(Map<String, Object> value) {
		this.properties = value;
	}

}
