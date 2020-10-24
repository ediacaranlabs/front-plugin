package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class MenuBarTag  extends AbstractSimpleComponent {

	public static final String CONTEXT_ID = MenuBarTag.class.getName() + ":CONTEXT";
	
	public static final String TEMPLATE  = "/components/menu-bar";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean sidebar;
	
	private String style; //light, dark
	
	private String background; //dark, primary
	
	private String position; //top, bottom, sticky
	
	private String expand; //sm, md, lg, xl
	
	private JspFragmentVarParser content;
	
	public MenuBarTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Boolean getSidebar() {
		return sidebar;
	}

	public void setSidebar(Boolean sidebar) {
		this.sidebar = sidebar;
	}

}
