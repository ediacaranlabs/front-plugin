package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.DataTableComponent;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="data-table", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DataTableTagComponent extends FormTagComponent {

	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	@Override
    protected Component createComponent() {
    	return new DataTableComponent();
    }
	
	@Override
	public String getType() {
		return "data-table";
	}
	
}
