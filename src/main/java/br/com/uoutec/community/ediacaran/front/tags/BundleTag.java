package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
		String packageID;
		Locale currentLocale;
		
    	if(basename == null) {
			
			String reqID = super.getRequestPath();
			packageID = LanguageManager.TEMPLATE_PACKAGE + (reqID.startsWith("/")? reqID : "/" + reqID);
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
	
}
