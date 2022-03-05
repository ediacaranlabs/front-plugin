package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="select", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class SelectTagComponent extends FieldFormTagComponent {

	public static final String TEMPLATE = "/components/select";
	
	/* ------------ Attr ---------------*/
	
	private Boolean autofocus;
	
	private Boolean readonly;

	private Boolean required;
	
	private Boolean multiple;
	
	private Integer sizeList;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String size;

	public SelectTagComponent() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			vars.put("empty",   label == null? "sr-only" : null);
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public Boolean getAutofocus() {
		return autofocus;
	}

	@TagAttribute
	public void setAutofocus(Boolean autofocus) {
		this.autofocus = autofocus;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	@TagAttribute
	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
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

	public Boolean getMultiple() {
		return multiple;
	}

	@TagAttribute
	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public String getSize() {
		return size;
	}

	@TagAttribute
	public void setSize(String size) {
		this.size = size;
	}

	public Integer getSizeList() {
		return sizeList;
	}

	@TagAttribute
	public void setSizeList(Integer sizeList) {
		this.sizeList = sizeList;
	}

}
