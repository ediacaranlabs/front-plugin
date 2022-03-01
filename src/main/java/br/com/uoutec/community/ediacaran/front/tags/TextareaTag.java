package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="textarea", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TextareaTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/textarea";
	
	/* ------------ Attr ---------------*/
	
	private Boolean autocomplete;
	
	private Boolean autofocus;

	private Integer cols;
	
	private Integer rows;
	
	private Integer maxlength;
	
	private Integer minlength;
	
	private String placeholder;
	
	private Boolean required;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	public TextareaTag() {
		super.setComponentType("text");
	}
	
    protected TagComponent createTagComponent() {
    	return new TagComponent() {
    		
    	    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    			vars.put("empty",   label == null? "sr-only" : null);
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

	public Integer getMaxlength() {
		return maxlength;
	}

	@TagAttribute
	public void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	@TagAttribute
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
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

	public Integer getCols() {
		return cols;
	}

	@TagAttribute
	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public Integer getRows() {
		return rows;
	}

	@TagAttribute
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getMinlength() {
		return minlength;
	}

	@TagAttribute
	public void setMinlength(Integer minlength) {
		this.minlength = minlength;
	}

}
