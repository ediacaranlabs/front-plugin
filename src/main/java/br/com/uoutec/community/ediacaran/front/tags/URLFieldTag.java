package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="urlfield", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class URLFieldTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/urlfield";
	
	/* ------------ Attr ---------------*/
	
	private Boolean autocomplete;
	
	private Boolean autofocus;

	private Integer maxlength;
	
	private Integer minlength;
	
	private String pattern;
	
	private String placeholder;
	
	private Boolean required;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private String size;

	private JspFragmentVarParser content;
	
	public URLFieldTag() {
		super.setComponentType("url");
	}
	
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
		vars.put("empty", label == null? "sr-only" : null);
    }
	
    protected String getDefaultTemplate() {
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

	public Integer getMinlength() {
		return minlength;
	}

	@TagAttribute
	public void setMinlength(Integer minlength) {
		this.minlength = minlength;
	}

	public String getPattern() {
		return pattern;
	}

	@TagAttribute
	public void setPattern(String pattern) {
		this.pattern = pattern;
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

	public String getSize() {
		return size;
	}

	@TagAttribute
	public void setSize(String size) {
		this.size = size;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
