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
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.Theme;

@Tag(
	name="include", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class IncludeTag extends SimpleTagSupport {
	
	private Boolean resolved;
	
	private String uri;
	
	public IncludeTag() {
	}

    public void doTag() throws JspException, IOException {
    	
    	String path;
    	ServletContext servletContext;
		PageContext pageContext = (PageContext) getJspContext();
    	
    	if(resolved != null && resolved.booleanValue()) {
    		path = uri;
    		servletContext = pageContext.getServletContext();
    	}
    	else {
	    	Component tagComponent = new Component();
	    	tagComponent.setPageContext((PageContext) getJspContext());
	    	
	    	Theme theme             = tagComponent.getTheme();
	    	String packageName      = tagComponent.getPackageTheme();
	    	
	    	path = theme.getTemplate(packageName) + uri;
	    	servletContext = pageContext.getServletContext();
	    	pageContext = (PageContext) getJspContext();
	    	
	    	String currentContext = servletContext.getContextPath().toLowerCase();
	    	String context = theme.getContext();
	    	String themeContext = context == null? null : context.toLowerCase();
	    	
	    	if(themeContext != null && !currentContext.equals(themeContext)) {
	    		servletContext = servletContext.getContext(context);
	    	}
    	}
    	
    	ImportResponseWrapper irw = new ImportResponseWrapper(pageContext, getJspContext().getOut()); 
    	
    	try {
    		boolean newContext = !servletContext.equals(pageContext.getServletContext());
    		
    		pageContext.getRequest().setAttribute("newContext", newContext);
    		
    		servletContext.getRequestDispatcher(path).include(pageContext.getRequest(), irw);
    	}
    	catch(ServletException e) {
    		throw new JspTagException("unable to load page " + path, e.getCause());
    	}
    	
    	if (irw.getStatus() < 200 || irw.getStatus() > 299) { 
            throw new JspTagException("unable to load page " + path + " [status=" + irw.getStatus() + "]"); 
        }
    	
    }

    protected String parseRequestId(String path, String contextPath){
    	return path;
    }

	public String getUri() {
		return uri;
	}

	@TagAttribute
	public void setUri(String uri) {
		this.uri = uri;
	}

	public Boolean getResolved() {
		return resolved;
	}

	@TagAttribute
	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}

	private class ImportResponseWrapper extends HttpServletResponseWrapper { 
		 
		 private ServletOutputStream sos; 
		 
		 private int status = 200; 
		 
		 private PageContext pageContext; 
		  
		 private JspWriter writter;
		 
		 //************************************************************ 
		 // Constructor and methods 
		 
		 /** Constructs a new ImportResponseWrapper. */ 
		 public ImportResponseWrapper(PageContext pc, JspWriter writter) { 
			 super((HttpServletResponse)pc.getResponse()); 
		     this.pageContext = pc;
		     this.writter = writter;
		     this.sos = new ServletOutputStream() {
				
				@Override
				public void write(int b) throws IOException {
					ImportResponseWrapper.this.writter.write(b);
				}

				@Override
				public boolean isReady() {
					return false;
				}

				@Override
				public void setWriteListener(WriteListener writeListener) {
					
				}
				
		     }; 		     
		 } 
		  
		 public PrintWriter getWriter() throws IOException { 
		     return new PrintWriter(pageContext.getOut()); 
		 } 
		  
		 public ServletOutputStream getOutputStream() { 
		     return sos; 
		 } 
		 
		 public void setContentType(String x) { 
		 } 
		 
		 public void setLocale(Locale x) { 
		 } 
		 
		 public void setStatus(int status) { 
		     this.status = status; 
		 } 
		 
		 public int getStatus() { 
		     return status; 
		 } 
		 
    }
	
}
