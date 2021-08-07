package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="prepend-field-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldGroupItemTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/field-group-item";
	
	public static final String TEMPLATE2  = "/components/content";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean text;
	
	private JspFragmentVarParser content;
	
	public FieldGroupItemTag() {
	}
	
    protected String getDefaultTemplate() {
    	return text == null || text? TEMPLATE : TEMPLATE2;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public Boolean getText() {
		return text;
	}

	@TagAttribute
	public void setText(Boolean text) {
		this.text = text;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
    
}
