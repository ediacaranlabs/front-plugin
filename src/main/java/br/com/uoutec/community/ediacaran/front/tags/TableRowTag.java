package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class TableRowTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/table-row-header";
	
	public static final String TEMPLATE_2  = "/components/table-row";
	
	/* ------------ Attr ---------------*/
	
	private String style;
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	public TableRowTag() {
	}
	
    protected String getDefaultTemplate() {
    	Object parent = this.getParentTag();
    	return parent instanceof TableHeaderTag? TEMPLATE : TEMPLATE_2;
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
