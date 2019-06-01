package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

public class BreadcrumbTag extends AbstractTag {

	public static final String TEMPLATE = "/bootstrap4/templates/components/breadcrumb";
	
	private String title;
	
	public BreadcrumbTag() {
	}
	
    public void doInnerTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr",  super.toAttrs());
			vars.put("title", title);
			vars.put("path",  new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}