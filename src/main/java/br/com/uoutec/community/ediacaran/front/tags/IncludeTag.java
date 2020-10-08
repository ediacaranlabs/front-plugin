package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import br.com.uoutec.community.ediacaran.front.PluginInstaller;
import br.com.uoutec.community.ediacaran.front.tema.Tema;
import br.com.uoutec.community.ediacaran.front.tema.TemaRegistry;

public class IncludeTag extends AbstractSimpleTag {
	
	private String uri;
	
	private String packageName;
	
	public IncludeTag() {
	}

    public void doTag() throws JspException, IOException {
    	
    	PageContext pageContext = (PageContext) getJspContext();
    	TemaRegistry temaRegistry = (TemaRegistry)pageContext.getServletContext().getAttribute(PluginInstaller.TEMA_REGISTRY);
    	Tema tema = temaRegistry.getCurrentTema();
    	
    	String context = tema.getContext();
    	String path = tema.getBase() +  "/" + packageName + uri;
    	
    	ServletContext servletContext = pageContext.getServletContext();
    	
    	String currentContext = servletContext.getContextPath().toLowerCase();
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
    
	
}
