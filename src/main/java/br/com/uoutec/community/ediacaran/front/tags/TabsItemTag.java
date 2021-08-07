package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.TagTemplate.VarParser;

@Tag(
	name="tabs-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TabsItemTag extends AbstractSimpleComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean active;

	private String icon;
	
	private VarParser content;
	
    public void doTag() throws JspException, IOException{
    	
    	Object parent = super.getParentTag();
    	
    	if(parent instanceof TabsTag) {
    		//StringWriter stringWriter = new StringWriter();
    		//this.getJspBody().invoke(stringWriter);
    		((TabsTag)parent).add(this);
    	}
    	
    }

	protected void beforePrepareVars(Map<String, Object> vars) {
		content = this.toVarParser();
		vars.put("show", Boolean.TRUE.equals(active)?  "show" : "");
		vars.put("selected", active);
	}
    
    protected String getDefaultTemplate() {
    	return null;
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

	public VarParser getContent() {
		return content;
	}

	public void setContent(VarParser content) {
		this.content = content;
	}
	
}
