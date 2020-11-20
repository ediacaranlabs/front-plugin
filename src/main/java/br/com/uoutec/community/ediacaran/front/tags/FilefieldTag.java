package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class FilefieldTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/filefield";
	
	/* ------------ Attr ---------------*/
	
	private String accept;
	
	private Boolean capture;
	
	private Boolean multiple;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private JspFragmentVarParser content;
	
	public FilefieldTag() {
		super.setComponentType("file");
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
		vars.put("empty", label == null? "sr-only" : null);
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public Boolean getCapture() {
		return capture;
	}

	public void setCapture(Boolean capture) {
		this.capture = capture;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
