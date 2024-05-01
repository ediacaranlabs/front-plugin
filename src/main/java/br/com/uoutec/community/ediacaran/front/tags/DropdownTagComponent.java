package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="dropdown", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DropdownTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/dropdown";
	
	public static final String TEMPLATE2 = "/components/split-dropdown";
	
	public static final String TEMPLATE_WRAPPER = "/components/dropdown-wrapper";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String style; //primary, secondary, success, info, warning, danger
	
	private String size; //lg, sm
	
	private Boolean split;
	
	private String variation; //up, right, left
	
	public DropdownTagComponent() {
		setWrapper(true);
	}
	
    public String getWrapperTemplate() {
    	return TEMPLATE_WRAPPER;
    }
    
    public String getDefaultTemplate() {
    	return split == null || !split? TEMPLATE : TEMPLATE2;
    }

	public String getLabel() {
		return label;
	}

	@TagAttribute(required=true)
	public void setLabel(String label) {
		this.label = label;
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

	public Boolean getSplit() {
		return split;
	}

	@TagAttribute
	public void setSplit(Boolean split) {
		this.split = split;
	}

	public String getVariation() {
		return variation;
	}

	@TagAttribute
	public void setVariation(String variation) {
		this.variation = variation;
	}

	@Override
	public String getType() {
		return "dropdown";
	}
	
}
