package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="table-col", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
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

	@TagAttribute
	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public Integer getRows() {
		return rows;
	}

	@TagAttribute
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

	@TagAttribute
	public void setStyle(String style) {
		this.style = style;
	}

}
