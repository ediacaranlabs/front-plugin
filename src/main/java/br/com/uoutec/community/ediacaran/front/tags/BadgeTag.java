package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
		name="badge", 
		uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class BadgeTag extends AbstractSimpleComponent {

	public static final String TEMPLATE       = "/components/badge";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String type; //navbar
	
	//private String style; //primary, secondary, success, danger, warning, info, light, dark
	
	private JspFragmentVarParser content;
	
	public BadgeTag() {
	}

    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public String getType() {
		return type;
	}

	@TagAttribute
	public void setType(String type) {
		this.type = type;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}
