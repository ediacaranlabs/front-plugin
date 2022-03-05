package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.theme.DelegateVarParser;

@Tag(
	name="testimonial-info", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TestimonialInfoTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/testimonial-info";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public TestimonialInfoTagComponent() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	Object parent = super.getParentTag();
    	
    	if(parent instanceof TestimonialTagComponent) {
    		TestimonialTagComponent tt = (TestimonialTagComponent)parent;
    		DelegateVarParser dvp = (DelegateVarParser)tt.getInfo();
    		dvp.setVarParser(this.toVarParser());
    	}
    	
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

}
