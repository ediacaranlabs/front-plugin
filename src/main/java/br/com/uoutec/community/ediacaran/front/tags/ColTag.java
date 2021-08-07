package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="col", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/designer", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ColTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/designer/col";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer size;
	
	private Integer offset;
	
	private Integer order;
	
	private JspFragmentVarParser content;
	
	public ColTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}

	public Integer getSize() {
		return size;
	}

	@TagAttribute
	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getOffset() {
		return offset;
	}

	@TagAttribute
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public Integer getOrder() {
		return order;
	}

	@TagAttribute
	public void setOrder(Integer order) {
		this.order = order;
	}

}
