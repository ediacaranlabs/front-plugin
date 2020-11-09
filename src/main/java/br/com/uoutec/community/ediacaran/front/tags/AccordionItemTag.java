package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

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
	
    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
    	Object parentTag = getParentTag();
		this.parentID = parentTag == null? null : ((AccordionTag)parentTag).getId();
    }
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
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
