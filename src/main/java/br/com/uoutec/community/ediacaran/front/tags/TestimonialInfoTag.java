package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.theme.DelegateVarParser;
import br.com.uoutec.community.ediacaran.front.theme.TagTemplate.VarParser;

public class TestimonialInfoTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/testimonial-info";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private VarParser content;
	
	public TestimonialInfoTag() {
	}
	
    public void doTag() throws JspException, IOException{
    	
    	Object parent = super.getParentTag();
    	
    	if(parent instanceof TestimonialTag) {
    		TestimonialTag tt = (TestimonialTag)parent;
    		DelegateVarParser dvp = (DelegateVarParser)tt.getInfo();
    		dvp.setVarParser(this.toVarParser());
    	}
    	
    }
	
	protected void beforePrepareVars(Map<String, Object> vars) {
		content = toVarParser();
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public VarParser getContent() {
		return content;
	}

	public void setContent(VarParser content) {
		this.content = content;
	}
	

}
