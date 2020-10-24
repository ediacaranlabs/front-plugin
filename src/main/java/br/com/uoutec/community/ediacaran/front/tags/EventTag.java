package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class EventTag extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/event";
	
	private String type;
	
	private String component;
	
	private JspFragmentVarParser content;


    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComponent() {
		if(component == null) {
			Object cp = super.getParentTag();
			if(cp != null) {
				if(cp instanceof AbstractSimpleComponent) {
					component = ((AbstractSimpleComponent)cp).getId();
				}
				else
				if(cp instanceof AbstractPanelComponent) {
					component = ((AbstractPanelComponent)cp).getId();
				}
			}
		}
		
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
    
}
