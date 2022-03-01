package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="breadcrumb-path", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class BreadcrumbPathTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/breadcrumb-path";
	
	public static final String TEMPLATE_ICON = "/components/icon";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String icon;
	
	private String text;
	
	private String lnk;
	
	public BreadcrumbPathTag() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getIcon() {
		return icon;
	}

	@TagAttribute
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	@TagAttribute(required=true)
	public void setText(String text) {
		this.text = text;
	}

	public String getLnk() {
		return lnk;
	}

	@TagAttribute
	public void setLnk(String lnk) {
		this.lnk = lnk;
	}
    
}
