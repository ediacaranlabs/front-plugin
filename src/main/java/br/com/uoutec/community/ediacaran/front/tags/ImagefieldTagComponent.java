package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="imagefield", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ImagefieldTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/imagefield";
	
	/* ------------ Attr ---------------*/
	
	private Boolean required;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private Integer width;
	
	private Integer height;
	
	private String border;
	
	private String button;
	
	private String src;
	
	public ImagefieldTagComponent() {
		super.setComponentType("file");
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			vars.put("empty", label == null? "sr-only" : null);
    	    }
    		
    	};
    }
	
    public Integer getWidth() {
		return width;
	}

	@TagAttribute(required=true)
	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	@TagAttribute(required=true)
	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getSrc() {
		return src == null? "/plugins/ediacaran/front/templates/default_template/front/plugins/imageField/img/default.png" : src;
	}

	@TagAttribute
	public void setSrc(String src) {
		this.src = src == null || src.trim().length() == 0? null : src;
	}

	public String getBorder() {
		return border;
	}

	@TagAttribute(displayName="type", required=true)
	public void setBorder(String border) {
		this.border = border;
	}

	public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getRequired() {
		return required;
	}

	@TagAttribute
	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getLabel() {
		return label;
	}

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	public String getButton() {
		return button;
	}

	@TagAttribute(required=true)
	public void setButton(String button) {
		this.button = button;
	}

	@Override
	public String getType() {
		return "textfield";
	}
	
}
