package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import br.com.uoutec.community.ediacaran.core.system.registry.LanguageManager;

public class BundleTag extends AbstractSimpleTag {
	
	public static final String LANGUAGE_MANAGER = "lang-manager";
	
	private Locale locale;
	
	private String var;
	
	private String basename;
	
	public BundleTag() {
	}

    public void doTag() throws JspException, IOException {
    	
    	PageContext pc = (PageContext)super.getJspContext();
    	HttpServletRequest request = (HttpServletRequest)pc.getRequest();
    	
		String packageID;
		Locale currentLocale;
		
    	if(basename == null) {
			packageID = LanguageManager.TEMPLATE_PACKAGE + request.getRequestURI().split("\\.")[0];
    	}
    	else {
    		packageID = LanguageManager.TEMPLATE_PACKAGE + "/" + basename;
    	}
    	
		PageContext pageContext = (PageContext) getJspContext();  
		
    	if(locale == null) {
    		currentLocale = (Locale)pageContext.getServletContext().getAttribute("locale");
    	}
    	else {
    		currentLocale = locale;
    	}
    	
		LanguageManager lm = (LanguageManager)pageContext.getServletContext().getAttribute(LANGUAGE_MANAGER);
		
		ResourceBundle value = lm.getResourceBundle(packageID, currentLocale);
		
    	super.setProperty(var, value);
    }

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}
	
}
