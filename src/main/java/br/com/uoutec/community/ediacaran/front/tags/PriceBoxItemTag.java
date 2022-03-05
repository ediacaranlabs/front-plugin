package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
		name="price-box-item", 
		uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class PriceBoxItemTag  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/price-box-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean checked;
	
	public PriceBoxItemTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getChecked() {
		return checked;
	}

	@TagAttribute
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
