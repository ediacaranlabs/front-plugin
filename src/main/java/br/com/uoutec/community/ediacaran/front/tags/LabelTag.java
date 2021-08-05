package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.theme.TagTemplate.VarParser;

public class LabelTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/label";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	
	private String size;
	
	private String style;

	private VarParser content;
	
	public LabelTag() {
	}
	
	protected void beforePrepareVars(Map<String, Object> vars) {
		content = toVarParser();
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public VarParser getContent() {
		return content;
	}

	public void setContent(VarParser content) {
		this.content = content;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
