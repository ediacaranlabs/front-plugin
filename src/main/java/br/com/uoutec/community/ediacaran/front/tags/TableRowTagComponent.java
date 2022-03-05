package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="table-row", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TableRowTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/table-row-header";
	
	public static final String TEMPLATE_2  = "/components/table-row";
	
	/* ------------ Attr ---------------*/
	
	private String style;
	
	/* ------------ Prop ---------------*/
	
	public TableRowTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	Object parent = this.getParentTag();
    	return parent instanceof TableHeaderTagComponent? TEMPLATE : TEMPLATE_2;
    }

	public String getStyle() {
		return style;
	}

	@TagAttribute
	public void setStyle(String style) {
		this.style = style;
	}

}
