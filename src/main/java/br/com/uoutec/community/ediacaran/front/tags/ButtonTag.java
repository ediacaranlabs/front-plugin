package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.theme.TagTemplate.VarParser;

public class ButtonTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/button";
	
	/* ------------ Attr ---------------*/
	
	private String action;
	
	private String ctype;
	
	private String method;
	
	private String target;

	private String actionType; //submit
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String icon;
	
	private String size;
	
	private String type;
	
	private Boolean block;
	
	private Boolean outline;

	private VarParser content;
	
	public ButtonTag() {
	}
	
	protected void beforePrepareVars(Map<String, Object> vars) {
		content = toVarParser();
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getOutline() {
		return outline;
	}

	public void setOutline(Boolean outline) {
		this.outline = outline;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public VarParser getContent() {
		return content;
	}

	public void setContent(VarParser content) {
		this.content = content;
	}

}
