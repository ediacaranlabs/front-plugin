package br.com.uoutec.community.ediacaran.front.tags;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="load-data", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class LoadDataTag  extends SimpleTagSupport {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Locale locale;
	
	private String context;
	
	private String file;
	
	private String var;
	
	public LoadDataTag() {
	}
	
    public void doTag() throws JspException, IOException {
    	Component tagComponent = new Component();
    	tagComponent.setPageContext((PageContext) getJspContext());

    	File root = getRootPath();
    	File base = file.startsWith("/")? root : getBasePath(tagComponent.getRequestPath(), root);
    	Map<Object,Object> dta = ReadData.loadData(file, base, root, locale);
    	/*
    			.loadData(
    								file, 
    								locale,
    								root, 
    								new File(baseWebApp, tagComponent.getRequestPath())
								);
    	*/
    	((PageContext)getJspContext()).getRequest().setAttribute(var == null? "vars" : var, dta);
	}

    private File getRootPath() {
    	ServletContext servletContext;
    	
    	if(context == null) {
    		servletContext = ((PageContext)getJspContext()).getServletContext();
    	}
    	else {
    		servletContext = ((PageContext)getJspContext()).getServletContext().getContext(context);
    	}
    			
		return new File(servletContext.getRealPath("/"));
    }

    private File getBasePath(String path, File root) {
    	File fullPath = new File(root, path);
		return root.equals(fullPath)? fullPath : fullPath.getParentFile();
    }
    
    protected void applyTemplate(String template, 
    		Map<String,Object> vars, Writer out){
    }

	public String getFile() {
		return file;
	}

	@TagAttribute(required=true)
	public void setFile(String file) {
		this.file = file;
	}

	public String getVar() {
		return var;
	}

	@TagAttribute
	public void setVar(String var) {
		this.var = var;
	}

	public String getContext() {
		return context;
	}

	public Locale getLocale() {
		return locale;
	}

	@TagAttribute
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@TagAttribute
	public void setContext(String context) {
		this.context = context;
	}

}
