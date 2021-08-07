package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.DelegateVarParser;
import br.com.uoutec.community.ediacaran.front.theme.TagTemplate.VarParser;

@Tag(
	name="testimonial", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TestimonialTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/testimonial";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String name;
	
	private String image;

	private VarParser info;
	
	private VarParser content;
	
	public TestimonialTag() {
	}
	
	protected void beforePrepareVars(Map<String, Object> vars) {
		content = toVarParser();
		info = new DelegateVarParser();
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getName() {
		return name;
	}

	@TagAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	@TagAttribute
	public void setImage(String image) {
		this.image = image;
	}

	public VarParser getContent() {
		return content;
	}

	public void setContent(VarParser content) {
		this.content = content;
	}

	public void setInfo(VarParser info) {
		this.info = info;
	}

	public VarParser getInfo() {
		return info;
	}

}
