package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class TreeviewGroupTag  extends AbstractSimpleComponent {

	public static final String CONTEXT_ID = TreeviewGroupTag.class.getName() + ":CONTEXT";

	public static final String TEMPLATE  = "/components/treeview-group";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	public TreeviewGroupTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
