package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
		name="sidebar", 
		uri="https://www.uoutec.com.br/ediacaran/tags/components", 
		bodycontent=BodyTypes.SCRIPTLESS
	)
public class SideBarTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/sidebar";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean autoAdjust;
	
	public SideBarTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getAutoAdjust() {
		return autoAdjust;
	}

	@TagAttribute
	public void setAutoAdjust(Boolean autoAdjust) {
		this.autoAdjust = autoAdjust;
	}

	@Override
	public String getType() {
		return "sidebar";
	}
	
}
