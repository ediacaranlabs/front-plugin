package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="label", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class LabelTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/label";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	
	private String size;
	
	private String style;

	public LabelTagComponent() {
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

	public String getSize() {
		return size;
	}

	@TagAttribute
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String getType() {
		return "label";
	}
	
}
