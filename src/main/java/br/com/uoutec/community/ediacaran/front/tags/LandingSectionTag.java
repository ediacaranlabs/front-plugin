package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class LandingSectionTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/landing-section";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String name;
	
	private String title;
	
	private String img;
	
	private String align;
	
	private JspFragmentVarParser content;
	
	public LandingSectionTag() {
	}
	
	public void afterPrepareVars(Map<String, Object> vars) {
		vars.put("align-left",  "right".equals(align)? " col-lg-offset-1 col-sm-push-6" : "" );
		vars.put("align-right", "right".equals(align)? " col-lg-offset-2" : "col-sm-pull-6" );
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
