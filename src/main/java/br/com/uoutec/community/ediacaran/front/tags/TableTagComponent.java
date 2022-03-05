package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="table", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TableTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/table";
	
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String style;
	
	public TableTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getStyle() {
		return style;
	}

	@TagAttribute
	public void setStyle(String style) {
		this.style = style;
	}

}
