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
import br.com.uoutec.ediacaran.web.WebUtil;

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
		
    	String contextName;
    	String path;
    	
    	ServletContext servletContext = request.getServletContext();
    	String currentContext = servletContext.getContextPath().toLowerCase();
    	
    	if(resolved) {
    		path = uri;
    		
    		String[] parts = path.split("\\:");
    		
    		if(parts.length > 1) {
    			contextName = parts[0];
    			path = parts[1];
    		}
    		else {
    			contextName = null;
    		}
    		
    	}
    	else {
    		
    		if(!uri.startsWith("/")) {
    	    	String reqID = WebUtil.getRequestPath(request);
    	    	reqID = reqID.replaceAll("\\+", "/");
    	    	reqID = reqID.replaceAll("/+", "/");
    	    	
    	    	int index = reqID.lastIndexOf("/");
    	    	
    	    	if(index != -1) {
    	    		path = reqID.substring(0, index) + "/" + uri;
    	    	}
    	    	else {
    	    		path = uri;
    	    	}
    			
    		}
    		else {
    	    	path = theme.getTemplate(packageName) + uri;
    		}
    		
	    	contextName	= theme.getContext();
    	}

    	if(contextName != null && !currentContext.equals(contextName)) {
    		servletContext = servletContext.getContext(contextName);
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

}
