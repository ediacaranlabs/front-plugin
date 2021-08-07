package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="checkbox", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class CheckboxTag extends ComponentFormTag {

	public static final String TEMPLATE = "/components/checkbox";
	
	/* ------------ Attr ---------------*/
	
	private Boolean selected;
	
	/* ------------ Prop ---------------*/
	
	private String label;
	
	private Boolean inline;

	private JspFragmentVarParser content;
	
	public CheckboxTag() {
		setComponentType("checkbox");
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
	
	public Boolean getSelected() {
		return selected;
	}

	@TagAttribute
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return label;
	}

	@TagAttribute
	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getInline() {
		return inline;
	}

	@TagAttribute
	public void setInline(Boolean inline) {
		this.inline = inline;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
