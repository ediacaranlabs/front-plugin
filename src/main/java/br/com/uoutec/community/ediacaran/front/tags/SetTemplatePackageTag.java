package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="setTemplatePackage", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class SetTemplatePackageTag extends SimpleTagSupport {
	
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

	@TagAttribute
	public void setName(String name) {
		this.name = name;
	}

}
