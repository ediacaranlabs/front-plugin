package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="filefield", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FilefieldTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/filefield";
	
	/* ------------ Attr ---------------*/
	
	private String accept;
	
	private Boolean capture;
	
	private Boolean multiple;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	public FilefieldTagComponent() {
		super.setComponentType("file");
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			vars.put("empty", label == null? "sr-only" : null);
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getAccept() {
		return accept;
	}

	@TagAttribute
	public void setAccept(String accept) {
		this.accept = accept;
	}

	public Boolean getCapture() {
		return capture;
	}

	@TagAttribute
	public void setCapture(Boolean capture) {
		this.capture = capture;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	@TagAttribute
	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getLabel() {
		return label;
	}

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

}
