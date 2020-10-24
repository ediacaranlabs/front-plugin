package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class SideBarTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/sidebar";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer size;
	
	private String align;
	
	private JspFragmentVarParser content;
	
	public SideBarTag() {
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
		int offset = "right".equalsIgnoreCase(this.align)? (this.size == null? 11 : 12 - this.size) : -1; 
		vars.put("offset", offset <= 0? "" : "offset-lg-" + offset + " offset-xl-" + offset);
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
