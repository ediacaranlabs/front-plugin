package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

public class BreadcrumbPathTag extends EdiacaranSimpleTagSupport {

	public static final String TEMPLATE = "bootstrap4/templates/components/breadcrumb-path";
	
	public static final String TEMPLATE_ICON = "bootstrap4/templates/components/icon";
	
	private String icon;
	
	private String text;
	
	private String lnk;
	
	public BreadcrumbPathTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("icon",  icon == null? null : new TemplateVarParser(TEMPLATE_ICON).put("icon", icon).put("size", 1));
			vars.put("text", text);
			vars.put("lnk", lnk);
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }
    
}
