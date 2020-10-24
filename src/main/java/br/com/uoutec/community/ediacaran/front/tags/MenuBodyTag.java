package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class MenuBodyTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/menu-body";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String menuID;

	private JspFragmentVarParser content;
	
	public MenuBodyTag() {
	}
	
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	Object id = menuID == null? ((MenuBarTag)getProperty(MenuBarTag.CONTEXT_ID)).getId() : menuID;
    	
    	if(id == null) {
    		throw new RuntimeException("menu id not found");
    	}
    	
    	vars.put("menu-id", id);
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

}
