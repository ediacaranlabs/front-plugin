package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class DescriptionTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/description";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String title;
	
	private Boolean truncate;
	
	private Integer titleWidth;
	
	private Integer contentWidth;
	
	private JspFragmentVarParser content;
	
	public DescriptionTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getTruncate() {
		return truncate;
	}

	public void setTruncate(Boolean truncate) {
		this.truncate = truncate;
	}

	public Integer getTitleWidth() {
		return titleWidth;
	}

	public void setTitleWidth(Integer titleWidth) {
		this.titleWidth = titleWidth;
	}

	public Integer getContentWidth() {
		return contentWidth;
	}

	public void setContentWidth(Integer contentWidth) {
		this.contentWidth = contentWidth;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
	
}
