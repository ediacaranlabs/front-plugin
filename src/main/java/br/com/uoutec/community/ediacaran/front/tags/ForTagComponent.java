package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="for", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ForTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/for";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer begin;

	private Integer end;

	private Integer step = 1;
	
	private String var;
	
	public ForTagComponent() {
	}

    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public Integer getBegin() {
		return begin;
	}

	@TagAttribute(required=true)
	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getEnd() {
		return end;
	}

	@TagAttribute(required=true)
	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getStep() {
		return step;
	}

	@TagAttribute
	public void setStep(Integer step) {
		this.step = step;
	}

	public String getVar() {
		return var;
	}

	@TagAttribute(required=true)
	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public String getType() {
		return "for";
	}
	
}
