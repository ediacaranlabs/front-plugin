package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

@Tag(
	name="include", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class IncludeTag extends AbstractSimpleTagComponent {
	
	public static final String TEMPLATE = "/components/content";
	
	private Boolean resolved;
	
	private String uri;
	
	public IncludeTag() {
	}

    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	@Override
	public String getType() {
		return "textfield";
	}

	public VarParser getContent() {
		return new IncludeVarParser(
				getUri(), 
				getResolved() == null? false : getResolved().booleanValue(), 
				getComponent().getTheme(), 
				getComponent().getPackageTheme(), 
				getComponent().getPageContext()
		);
	}
	
	public String getUri() {
		return uri;
	}

	@TagAttribute
	public void setUri(String uri) {
		this.uri = uri;
	}

	public Boolean getResolved() {
		return resolved;
	}

	@TagAttribute
	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}
	
}
