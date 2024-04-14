package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.FieldComponent;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
		name="tinymce", 
		uri="https://www.uoutec.com.br/ediacaran/tags/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class TinymceTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/tinymce";
	
	/* ------------ Attr ---------------*/
	
	private Integer maxlength;
	
	private Integer minlength;
	
	private Boolean required;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	public TinymceTagComponent() {
		super.setComponentType("text");
	}

    protected Component createComponent() {
    	return new FieldComponent() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    			vars.put("empty",   label == null? "sr-only" : null);
    	    }
    		
    	};
    }
	
    public Integer getMaxlength() {
		return maxlength;
	}

	@TagAttribute
	public void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}

	public Integer getMinlength() {
		return minlength;
	}

	@TagAttribute
	public void setMinlength(Integer minlength) {
		this.minlength = minlength;
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

	public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	public String getType() {
		return "tinymce";
	}

}
