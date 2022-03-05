package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="tabs-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TabsItemTagComponent extends AbstractSimpleTagComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean active;

	private String icon;
	
    public void doTag() throws JspException, IOException{

    	Object parent = super.getParentTag();
    	
    	if(parent instanceof TabsTagComponent) {
    		((TabsTagComponent)parent).add(this.getComponent());
    	}
    	
    }

    protected Component createComponent() {
    	return new Component() {
    		
    		protected void beforePrepareVars(Map<String, Object> vars) {
    			vars.put("show", Boolean.TRUE.equals(active)?  "show" : "");
    			vars.put("selected", active);
    		}
    		
    	};
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
	
}
