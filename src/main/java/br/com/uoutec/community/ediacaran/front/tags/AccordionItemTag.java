package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;;

public class AccordionItemTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/accordion-item";

	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	/* ------------ Private Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	private String parentID;
	
	public AccordionItemTag() {
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
		
    	Object parentTag = getParentTag();
		this.parentID = parentTag == null? null : ((AccordionTag)parentTag).getId();
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}	
    
}
