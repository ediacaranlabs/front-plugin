package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
		name="blockquote", 
		uri="https://www.uoutec.com.br/ediacaran/tags/components", 
		bodycontent=BodyTypes.SCRIPTLESS
		)
public class BlockquoteTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE      = "/components/blockquote";
	
	public static final String CITE_TEMPLATE = "/components/cite";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String src;
	
	private String cite;
	
	public BlockquoteTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getCite() {
		return cite;
	}

	@TagAttribute
	public void setCite(String cite) {
		this.cite = cite;
	}
	
	public String getSrc() {
		return src;
	}

	@TagAttribute
	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public String getType() {
		return "blockquote";
	}
	
}
