package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="data-table", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DataTableTagComponent extends AbstractSimpleTagComponent {

	public static final String DATA_TABLE_PROPERTY = DataTableTagComponent.class.getName() + ":object";
	
	public static final String TEMPLATE = "/components/data-table";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String action;
	
	public DataTableTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getAction() {
		return action;
	}

	@TagAttribute(required=true)
	public void setAction(String action) {
		this.action = action;
	}

	protected void beforeBuildComponent(Component component) {
		DataTableTagComponent tag = 
				(DataTableTagComponent) super.getProperty(DATA_TABLE_PROPERTY);
		
		if(tag != null) {
			throw new IllegalStateException("multiples <data-table/>");
		}
		
		super.setProperty(DATA_TABLE_PROPERTY, this);
	}

	protected void afterBuildComponent(Component component) {
		super.setProperty(DATA_TABLE_PROPERTY, null);
	}
	
	@Override
	public String getType() {
		return "data-table";
	}
	
}
