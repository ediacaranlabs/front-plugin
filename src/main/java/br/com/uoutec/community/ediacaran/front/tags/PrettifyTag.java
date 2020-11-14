package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.system.tema.TagTemplate.VarParser;

public class PrettifyTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/prettify";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean linenums;
	
	private VarParser content;
	
	public PrettifyTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new EscapeVarParser(getJspBody());
	}

	public Boolean getLinenums() {
		return linenums;
	}

	public void setLinenums(Boolean linenums) {
		this.linenums = linenums;
	}

	public VarParser getContent() {
		return content;
	}

	public void setContent(VarParser content) {
		this.content = content;
	}
    
}
