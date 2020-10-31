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

public class IncludeTag extends AbstractSimpleComponent {
	
	private String uri;
	
	public IncludeTag() {
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

	private class ImportResponseWrapper extends HttpServletResponseWrapper { 
		 
		 private ServletOutputStream sos; 
		 
		 private int status = 200; 
		 
		 private PageContext pageContext; 
		  
		 //************************************************************ 
		 // Constructor and methods 
		 
		 /** Constructs a new ImportResponseWrapper. */ 
		 public ImportResponseWrapper(PageContext pc) { 
			 super((HttpServletResponse)pc.getResponse()); 
		     this.pageContext = pc;
		     this.sos = new ServletOutputStream() {
				
				@Override
				public void write(int b) throws IOException {
					pageContext.getOut().write(b);
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
