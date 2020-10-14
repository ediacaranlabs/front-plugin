package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import br.com.uoutec.community.ediacaran.front.tema.Tema;

public class IncludeTag extends AbstractSimpleTag {
	
	private String uri;
	
	public IncludeTag() {
	}

    public void doTag() throws JspException, IOException {
    	
    	PageContext pageContext = (PageContext) getJspContext();
    	Tema tema               = getTema();
    	String packageName      = getTemaPackage();
    	
    	String path = tema.getTemplate(packageName) + uri;
    	
    	ServletContext servletContext = pageContext.getServletContext();
    	
    	String currentContext = servletContext.getContextPath().toLowerCase();
    	String context = tema.getContext();
    	String temaContext = context == null? null : context.toLowerCase();
    	
    	if(temaContext != null && !currentContext.equals(temaContext)) {
    		servletContext = servletContext.getContext(context);
    	}
    	
    	try {
    		servletContext.getRequestDispatcher(path).include(pageContext.getRequest(), pageContext.getResponse());
    	}
    	catch(ServletException e) {
    		throw new JspException(e);
    	}
    	
    }

    protected String parseRequestId(String path, String contextPath){
    	return path;
    }

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
