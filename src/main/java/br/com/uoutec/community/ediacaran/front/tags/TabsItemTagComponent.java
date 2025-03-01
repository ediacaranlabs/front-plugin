package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.TabsItemComponent;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="tabs-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TabsItemTagComponent extends AbstractSimpleTagComponent {
	
	public static final String TEMPLATE  = "/components/content";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean active;

	private String icon;
	
    protected Component createComponent() {
    	return new TabsItemComponent();
    }
    
	protected void beforeBuildComponent(Component component) {
		
    	Object parent = getParentTag();
    	
    	if(parent instanceof TabsTagComponent) {
    		((TabsTagComponent)parent).add(this.getComponent());
    	}
		
	}
    
    public Map<String, Object> getProperties(Set<String> defaultProperties, Set<String> emptyProperties){
    	Map<String,Object> props = super.getProperties(defaultProperties, emptyProperties);
    	props.put("show", Boolean.TRUE.equals(active)?  "show" : "");
    	props.put("selected", active);
    	return props;
    }
    
	public String getTitle() {
		return title;
	}

	@TagAttribute
	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getActive() {
		return active;
	}

	@TagAttribute
	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getIcon() {
		return icon;
	}

	@TagAttribute
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	
	@Override
	public String getType() {
		return "tabs-item";
	}
	
}
