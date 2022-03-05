package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.DelegateVarParser;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

@Tag(
	name="testimonial", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TestimonialTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/testimonial";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String name;
	
	private String image;

	private VarParser info;
	
	public TestimonialTagComponent() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		protected void beforePrepareVars(Map<String, Object> vars) {
    			info = new DelegateVarParser();
    		}
    		
    	};
    }
	
    public String getDefaultTemplate() {
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

	public void setInfo(VarParser info) {
		this.info = info;
	}

	public VarParser getInfo() {
		return info;
	}

}
