package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.FieldComponent;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="dateField", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DateFieldTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/datefield";
	
	/* ------------ Attr ---------------*/
	
	private Boolean autocomplete;
	
	private Boolean autofocus;

	private Boolean required;
	
	private String min;

	private String max;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String size;

	public DateFieldTagComponent() {
		super.setComponentType("date");
	}
	
    protected Component createComponent() {
    	return new FieldComponent() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			super.beforePrepareVars(vars);
    			vars.put("empty", label == null? "sr-only" : null);
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public Boolean getAutocomplete() {
		return autocomplete;
	}

	@TagAttribute
	public void setAutocomplete(Boolean autocomplete) {
		this.autocomplete = autocomplete;
	}

	public Boolean getAutofocus() {
		return autofocus;
	}

	@TagAttribute
	public void setAutofocus(Boolean autofocus) {
		this.autofocus = autofocus;
	}

	public String getMin() {
		return min;
	}

	@TagAttribute
	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	@TagAttribute
	public void setMax(String max) {
		this.max = max;
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

	public String getSize() {
		return size;
	}

	@TagAttribute
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String getType() {
		return "textfield";
	}
	
}
