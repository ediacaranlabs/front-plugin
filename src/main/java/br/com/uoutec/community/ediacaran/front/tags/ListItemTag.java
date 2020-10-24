package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class ListItemTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/list-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean inline;
	
	private JspFragmentVarParser content;
	
	public ListItemTag() {
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		ListTag list = (ListTag) getJspContext().getAttribute(ListTag.PARENT);
		this.inline  = "inline".equals(list.getStyle());
		this.content = new JspFragmentVarParser(getJspBody());
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getInline() {
		return inline;
	}

	public void setInline(Boolean inline) {
		this.inline = inline;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
