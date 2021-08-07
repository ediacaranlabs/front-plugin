package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="table-footer", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TableFooterTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/table-foot";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser content;
	
	public TableFooterTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

}