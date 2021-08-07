package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="breadcrumb", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BreadcrumbTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/breadcrumb";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private JspFragmentVarParser path;
	
	public BreadcrumbTag() {
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.path = new JspFragmentVarParser(getJspBody());
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	public String getTitle() {
		return title;
	}

	@TagAttribute(required=true)
	public void setTitle(String title) {
		this.title = title;
	}

	public JspFragmentVarParser getPath() {
		return path;
	}

	public void setPath(JspFragmentVarParser path) {
		this.path = path;
	}

}
