package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="treeview-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TreeviewItemTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/treeview-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String href;
	
	public TreeviewItemTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getHref() {
		return href;
	}

	@TagAttribute
	public void setHref(String href) {
		this.href = href;
	}

}
