package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.system.i18n.LanguageRegistry;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;
import br.com.uoutec.ediacaran.core.plugins.PluginConfigurationMetadata;
import br.com.uoutec.ediacaran.core.plugins.PluginType;

@Tag(
	name="setBundle", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class BundleTag extends SimpleTagSupport {
	
	private Locale locale;
	
	private String var;
	
	private String basename;
	
	public BundleTag() {
	}

    public void doTag() throws JspException, IOException {
    	
    	Component tagComponent = new Component();
    	tagComponent.setPageContext((PageContext) getJspContext());
    	
    	PluginType pt = EntityContextPlugin.getEntity(PluginType.class);
    	PluginConfigurationMetadata pcm = pt.getConfiguration().getMetadata();
    	
    	String reqID = tagComponent.getRequestPath();
    	reqID = reqID.split("\\.")[0];
    	reqID = reqID.replaceAll("/+", "/");
    	reqID = reqID.replaceAll("^/templates", "");
    	reqID = "/" + pcm.getSupplier() + "/" + pcm.getCode() + reqID;
    	//reqID = reqID.substring(ContextManager.BASE_CONTEXT.length());
    	
		String packageID;
		Locale currentLocale;
		
    	if(basename == null) {
			packageID = LanguageRegistry.TEMPLATE_PACKAGE + reqID;
    	}
    	else {
    		packageID = LanguageRegistry.TEMPLATE_PACKAGE + "/" + basename;
    	}
    	
		PageContext pageContext = (PageContext) getJspContext();  
		
    	if(locale == null) {
    		currentLocale = (Locale)pageContext.getServletContext().getAttribute("locale");
    	}
    	else {
    		currentLocale = locale;
    	}
    	
		//MessageBundle lm = (MessageBundle)pageContext.getServletContext().getAttribute(Constants.MESSAGEBUNDLE);
    	LanguageRegistry lm = EntityContextPlugin.getEntity(LanguageRegistry.class);
    	
		ResourceBundle value = lm.getResourceBundle(packageID, currentLocale);
		
		if(value == null) {
			throw new IllegalStateException(" not found: " + packageID);
		}
		
		tagComponent.setProperty(var, new LocalizationContext(value, locale));
    }

    protected String parseRequestId(String path, String contextPath){
    	return path;
    }
    
	public Locale getLocale() {
		return locale;
	}

	@TagAttribute
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getVar() {
		return var;
	}

	@TagAttribute
	public void setVar(String var) {
		this.var = var;
	}

	public String getBasename() {
		return basename;
	}

	@TagAttribute
	public void setBasename(String basename) {
		this.basename = basename;
	}
	
}
