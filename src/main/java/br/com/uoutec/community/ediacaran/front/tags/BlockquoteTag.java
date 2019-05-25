package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

public class BlockquoteTag extends EdiacaranSimpleTagSupport {

	public static final String TEMPLATE = "bootstrap4/templates/components/blockquote";
	
	private String cite;
	
	public BlockquoteTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr", super.toAttrs(null, null));
			vars.put("body", new JspFragmentVarParser(getJspBody()));
			vars.put("cite", cite == null? "" : "<cite>" + this.cite + "</cite>");
			
			TemplatesManager.getTemplatesManager()
				.apply(
					this.getTemplate() == null? TEMPLATE : this.getTemplate(), 
					vars, 
					getJspContext().getOut()
				);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

	public String getCite() {
		return cite;
	}

	public void setCite(String cite) {
		this.cite = cite;
	}
	
}
