package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ListTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE   = "/components/list";
	
	public static final String TEMPLATE2  = "/components/ordered-list";
	
	public static final String PARENT = ListTag.class.getSimpleName() + ":PARENT";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String style; //inline, unstyled, ordered
	
	private JspFragmentVarParser content;
	
	public ListTag() {
	}
	
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	getJspContext().setAttribute(PARENT, this);
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	getJspContext().removeAttribute(PARENT);
    }

    protected String getDefaultTemplate() {
    	return "ordered".equals(this.style)? TEMPLATE2 : TEMPLATE;
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

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
