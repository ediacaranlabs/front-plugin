package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class TableColTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/table-col-header";
	
	public static final String TEMPLATE_2  = "/components/table-col";
	
	/* ------------ Attr ---------------*/
	
	private Integer cols;
	
	private Integer rows;
	
	private String style;
	
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

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
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
