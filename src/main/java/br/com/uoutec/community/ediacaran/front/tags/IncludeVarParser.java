package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;

public class IncludeVarParser implements VarParser{

	private boolean resolved;
	
	private String uri;
	
	private HttpServletResponse response;
	
	private HttpServletRequest request;
	
	private Theme theme;
	
	private String packageName;
	
	public IncludeVarParser(String uri, boolean resolved, Theme theme, String packageName, PageContext context) {
		this.uri = uri;
		this.resolved = resolved;
		this.theme = theme;
		this.packageName = packageName;
		this.request = (HttpServletRequest) context.getRequest();
		this.response = (HttpServletResponse) context.getResponse();
	}
	
	@Override
	public void parse(Writer writter) throws IOException {
		
    	String path;
    	ServletContext servletContext = request.getServletContext();
    	
    	if(resolved) {
    		path = uri;
    	}
    	else {
	    	path 					= theme.getTemplate(packageName) + uri;
	    	String currentContext 	= servletContext.getContextPath().toLowerCase();
	    	String context 			= theme.getContext();
	    	String themeContext 	= context == null? null : context.toLowerCase();
	    	
	    	if(themeContext != null && !currentContext.equals(themeContext)) {
	    		servletContext = servletContext.getContext(context);
	    	}
    	}
    	
    	ImportResponseWrapper irw = new ImportResponseWrapper(response, writter); 
    	
    	try {
    		boolean newContext = !servletContext.equals(request.getServletContext());
    		
    		request.setAttribute("newContext", newContext);
    		
    		servletContext.getRequestDispatcher(path).include(request, irw);
    	}
    	catch(ServletException e) {
    		throw new IOException("unable to load page " + path, e.getCause());
    	}
    	
    	if (irw.getStatus() < 200 || irw.getStatus() > 299) { 
            throw new IOException("unable to load page " + path + " [status=" + irw.getStatus() + "]"); 
        }		
	}

	@Override
	public String parse() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
