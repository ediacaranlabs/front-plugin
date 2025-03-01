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
public class TabsTagComponent extends AbstractSimpleTagComponent {

	//private static final long serialVersionUID = 748182107582888257L;

	//private static final String CONTENT_ITEM = "/components/tabs-content-item";
	
	//private static final String HEADER_ITEM = "/components/tabs-header-item";

	public static final String TEMPLATE  = "/components/tabs";
	
	/*
	private TemplateListVarsParser header;
	
	private TemplateListVarsParser body;
	*/
	
	private String style;
	
	//private int index;
	
	public TabsTagComponent() {
		//this.index = 0;
	}
	
	public void add(Component value) {

		TabsComponent c = (TabsComponent) getComponent();
		c.addItem((TabsItemComponent)value);
		
		/*
		index++;
		
		header.createNewItem(value)
		.put("parentID", getId())
		.put("id", value.getId());
		
		body.createNewItem(value)
		.put("parentID", getId())
		.put("id", value.getId());
		*/
		
	}
	
    protected Component createComponent() {
    	return new TabsComponent();
    }
	
	/*
	protected void beforeBuildComponent(Component component) {
    	this.index   = 1;
		this.header  = new TemplateListVarsParser(HEADER_ITEM, component.getPackageTheme(), component.getTheme());
		this.body = new TemplateListVarsParser(CONTENT_ITEM, component.getPackageTheme(), component.getTheme());
	}
	*/
	/*
	protected void afterBuildComponent(Component component) {
    	this.index   = 0;
		this.header  = null;
		this.body = null;
	}
	*/
	
    @Deprecated
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

	/*
	public int getNextIndex() {
		return ++index;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    */
	
	@Override
	public String getType() {
		return "tabs";
	}
	
}
