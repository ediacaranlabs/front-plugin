package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.JavascriptConverterVarParser;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

@Tag(
	name="jsTemplate", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class JSTemplateTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/jstemplate";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	public JSTemplateTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
    protected VarParser toVarParser() {
		return new JavascriptConverterVarParser(getJspBody());
    }
    
	@Override
	public String getType() {
		return "jsTemplate";
	}
	
}
