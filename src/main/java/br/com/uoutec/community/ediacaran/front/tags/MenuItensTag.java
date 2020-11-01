package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class MenuItensTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/menu-itens";
	
	public static final String TEMPLATE_2  = "/components/menu-itens-menu";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	private MenuTag menu;
	
	private String align; //left right
	 
	public MenuItensTag() {
	}
	
	protected void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
    	if(menu != null) {
    		vars.put("parent-id", menu.getId());
    	}
    }
	
    protected String getDefaultTemplate() {
		menu = (MenuTag)getProperty(MenuTag.CONTEXT_ID);
    	return menu != null? TEMPLATE_2 : TEMPLATE;
    }

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

}
