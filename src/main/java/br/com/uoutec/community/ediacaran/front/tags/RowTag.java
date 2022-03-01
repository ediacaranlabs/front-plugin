package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="row", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class RowTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/designer/row";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String style; //form
	
	public RowTag() {
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
