package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

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
    	
		if(super.isWrapper()) {
			StringBuilder b = new StringBuilder("<div ")
				.append(this.toAttrs())
				.append(" >");
			getJspContext().getOut().write(b.toString());
		}
    	
		Object oldParent = getProperty(PARENT_TAG);

		setProperty(PARENT_TAG, this);
    	doInnerTag();
    	setProperty(PARENT_TAG, oldParent);
    	
		if(super.isWrapper()) {
    		getJspContext().getOut().write("</div>");
    	}
    }
	
	public String toAttrs() {
		return getProperty(FORM) == null? super.toAttrs() : "";
	}

}
