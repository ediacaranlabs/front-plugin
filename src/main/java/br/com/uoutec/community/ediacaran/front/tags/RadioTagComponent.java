package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="radio", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
	)
public class RadioTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/radio";
	
	/* ------------ Attr ---------------*/
	
	private Boolean selected;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private Boolean inline;
	
	public RadioTagComponent() {
		setComponentType("radio");
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

}
