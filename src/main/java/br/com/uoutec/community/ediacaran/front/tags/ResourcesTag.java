package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import br.com.uoutec.community.ediacaran.system.tema.Theme;

public class ResourcesTag extends AbstractSimpleComponent {
	
	private String uri;
	
	public ResourcesTag() {
	}

    public void doTag() throws JspException, IOException {
    	
    	PageContext pageContext = (PageContext) getJspContext();
    	Theme tema               = getTheme();
    	String packageName      = getTemaPackage();
    	
    	String path = tema.getTemplate(packageName) + uri;
    	
    	ServletContext servletContext = pageContext.getServletContext();
    	
    	String currentContext = servletContext.getContextPath().toLowerCase();
    	String context = tema.getContext();
    	String temaContext = context == null? null : context.toLowerCase();
    	
    	if(temaContext != null && !currentContext.equals(temaContext)) {
    		servletContext = servletContext.getContext(context);
    	}
    	
    	ImportResponseWrapper irw = new ImportResponseWrapper(pageContext); 
    	
    	try {
    		servletContext.getRequestDispatcher(path).include(pageContext.getRequest(), irw);
    	}
    	catch(ServletException e) {
    		throw new JspTagException(e);
    	}
    	
    	if (irw.getStatus() < 200 || irw.getStatus() > 299) { 
            throw new JspTagException(irw.getStatus() + " " + path); 
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

	@Override
	protected String getDefaultTemplate() {
		return null;
	}

}
