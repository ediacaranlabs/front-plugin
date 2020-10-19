package br.com.uoutec.community.ediacaran.front.tags.front;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public class SetTemplatePackageTag extends AbstractSimpleComponent {
	
	public static final String PACKAGE_NAME = SetTemplatePackageTag.class.getSimpleName() + ":package-name";
	
	private String name;
	
	public SetTemplatePackageTag() {
	}

    public void doTag() throws JspException, IOException {
    	PageContext pageContext = (PageContext) getJspContext();
    	pageContext.setAttribute(PACKAGE_NAME, name);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
}
