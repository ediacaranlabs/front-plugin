package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.JavascriptConverterVarParser;
import br.com.uoutec.community.ediacaran.front.components.TemplateComponent;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

@Tag(
	name="template", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class TemplateTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/template";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String var;
	
	public TemplateTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
    protected VarParser toVarParser() {
		return new JavascriptConverterVarParser(getJspBody());
    }
    
    protected Component createComponent() {
    	return new TemplateComponent();
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
		return "response";
	}
	
}
