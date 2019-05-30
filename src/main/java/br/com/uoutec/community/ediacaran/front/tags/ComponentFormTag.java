package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.StringPattern;

public abstract class ComponentFormTag extends AbstractTag {

	public static final String TEMPLATE = "bootstrap4/templates/components/form-control";
	
	public static final String FORM = ComponentFormTag.class.getSimpleName() + ":form";
	
	public ComponentFormTag() {
	}
	
    public void doTag() throws JspException, IOException {
		
    	if(getProperty(FORM) == null) {
    		super.doTag();
    		return;
    	}
		
		Object oldParent = getProperty(PARENT_TAG);

		setProperty(PARENT_TAG, this);
		
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("attr",    super.toAttrs());
			vars.put("component", new StringPattern.AbstractVarParser() {
				
				@Override
				public void parse(Writer writter) throws IOException {
					try {
						doInnerTag();
					} catch (JspException e) {
						throw new IOException(e);
					}
				}
				
			});
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
		
    	doInnerTag();
    	setProperty(PARENT_TAG, oldParent);
    	
    }
	
	public String toAttrs() {
		return getProperty(FORM) == null? super.toAttrs() : "";
	}

}
