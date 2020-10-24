package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

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
