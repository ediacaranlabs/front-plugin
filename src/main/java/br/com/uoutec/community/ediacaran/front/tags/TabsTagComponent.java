package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.TabsComponent;
import br.com.uoutec.community.ediacaran.front.components.TabsItemComponent;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="tabs", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TabsTagComponent extends AbstractBodyTagComponent {

	private static final long serialVersionUID = 748182107582888257L;

	private String style;
	
	public void add(Component value) {

		TabsComponent c = (TabsComponent) getComponent();
		c.addItem((TabsItemComponent)value);
		
	}
	
    protected Component createComponent() {
    	return new TabsComponent();
    }
	
	public String getStyle() {
		return style;
	}

	@TagAttribute
	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public String getType() {
		return "tabs";
	}
	
}
