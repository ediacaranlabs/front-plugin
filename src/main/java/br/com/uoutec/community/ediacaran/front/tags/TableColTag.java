package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class TableColTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/table-col-header";
	
	public static final String TEMPLATE_2  = "/components/table-col";
	
	/* ------------ Attr ---------------*/
	
	private Integer size;
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	public TableColTag() {
	}
	
    protected String getDefaultTemplate() {
    	Object parent = this.getParentTag();
    	return parent instanceof TableHeaderTag? TEMPLATE : TEMPLATE_2;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
