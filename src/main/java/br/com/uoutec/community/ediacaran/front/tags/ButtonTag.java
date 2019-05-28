package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class ButtonTag extends EdiacaranSimpleTagSupport {

	public static final String TEMPLATE             = "bootstrap4/templates/components/button";
	
	@SuppressWarnings("serial")
	private static final Set<String> ignore = new HashSet<String>() {{
		add("class");
	}};
	
	private String title;
	
	public ButtonTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr",  super.toAttrs(null, ignore));
			vars.put("title", title);
			vars.put("class", super.getClassStyle());
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
