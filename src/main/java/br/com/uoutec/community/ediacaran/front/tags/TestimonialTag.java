package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.system.theme.DelegateVarParser;
import br.com.uoutec.community.ediacaran.system.theme.TagTemplate.VarParser;

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

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

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
