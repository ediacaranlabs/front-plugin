package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;

@Tag(
	name="flexslider", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FlexsliderTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/flexslider";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private JspFragmentVarParser imgs;
	
	public FlexsliderTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.imgs = new JspFragmentVarParser(getJspBody());
	}
    
	public JspFragmentVarParser getImgs() {
		return imgs;
	}

	public void setImgs(JspFragmentVarParser imgs) {
		this.imgs = imgs;
	}
    
}
