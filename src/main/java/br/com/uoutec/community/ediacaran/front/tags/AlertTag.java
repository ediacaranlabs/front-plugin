package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.system.tema.TagTemplate.VarParser;

public class AlertTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/alert";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	
	private String style;

	private VarParser content;
	
	public AlertTag() {
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

}
