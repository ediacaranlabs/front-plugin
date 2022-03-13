package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="checkbox", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class CheckboxTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/checkbox";
	
	/* ------------ Attr ---------------*/
	
	private Boolean selected;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private Boolean inline;

	public CheckboxTagComponent() {
		setComponentType("checkbox");
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getSelected() {
		return selected;
	}

	@TagAttribute
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return label;
	}

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getInline() {
		return inline;
	}

	@TagAttribute
	public void setInline(Boolean inline) {
		this.inline = inline;
	}

	@Override
	public String getType() {
		return "checkbox";
	}
	
}
