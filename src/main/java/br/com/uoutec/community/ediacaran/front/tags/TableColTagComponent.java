package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="table-col", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TableColTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/table-col-header";
	
	public static final String TEMPLATE_2  = "/components/table-col";
	
	/* ------------ Attr ---------------*/
	
	private Integer cols;
	
	private Integer rows;
	
	//private String style;
	
	/* ------------ Prop ---------------*/
	
	public TableColTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	Object parent = this.getParentTag();
    	return parent instanceof TableHeaderTagComponent? TEMPLATE : TEMPLATE_2;
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

	@Override
	public String getType() {
		return "table-col";
	}
	
}
