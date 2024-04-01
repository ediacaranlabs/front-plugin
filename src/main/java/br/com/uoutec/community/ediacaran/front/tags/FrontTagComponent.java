package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.JavascriptConverterVarParser;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

public abstract class FrontTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/front-script";
	
	/* ------------ Attr ---------------*/
	
	public FrontTagComponent() {
	}
	
    public String getWrapperTemplate() {
    	return TEMPLATE;
    }
	
    protected VarParser toVarParser() {
		return new JavascriptConverterVarParser(getJspBody());
    }
    
}
