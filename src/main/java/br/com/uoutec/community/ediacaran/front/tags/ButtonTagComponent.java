package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="button", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ButtonTagComponent extends FieldFormTagComponent {

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
	
	private Boolean block;
	
	private Boolean outline;

	public ButtonTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getIcon() {
		return icon;
	}

	@TagAttribute
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAction() {
		return action;
	}

	@TagAttribute
	public void setAction(String action) {
		this.action = action;
	}

	public String getCtype() {
		return ctype;
	}

	@TagAttribute
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getMethod() {
		return method;
	}

	@TagAttribute
	public void setMethod(String method) {
		this.method = method;
	}

	public String getTarget() {
		return target;
	}

	@TagAttribute
	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getOutline() {
		return outline;
	}

	@TagAttribute
	public void setOutline(Boolean outline) {
		this.outline = outline;
	}

	public String getLabel() {
		return label;
	}

	@TagAttribute(required=true)
	public void setLabel(String label) {
		this.label = label;
	}

	public String getSize() {
		return size;
	}

	@TagAttribute
	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return "button";
	}

	public Boolean getBlock() {
		return block;
	}

	@TagAttribute
	public void setBlock(Boolean block) {
		this.block = block;
	}

	public String getActionType() {
		return actionType;
	}

	@TagAttribute
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
