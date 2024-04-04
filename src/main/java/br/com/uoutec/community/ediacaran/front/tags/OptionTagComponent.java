package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="option", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class OptionTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/option";
	
	/* ------------ Attr ---------------*/
	
	private String selected;
	
	private String label;
	
	/* ------------ Prop ---------------*/

	public OptionTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected == null? null : selected.toString(); 
	}
	
	@TagAttribute
	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return label;
	}

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getType() {
		return "option";
	}
	
}
