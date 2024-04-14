package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.DataResultComponent;
import br.com.uoutec.community.ediacaran.front.components.JavascriptConverterVarParser;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;

@Tag(
	name="data-result", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class DataResultTagComponent extends AbstractSimpleTagComponent {

	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String var;
	
	private String from;
	
	public DataResultTagComponent() {
	}
	
    protected Component createComponent() {
    	return new DataResultComponent();
    }
    
    protected VarParser toVarParser() {
		return new JavascriptConverterVarParser(getJspBody());
    }
    
	public String getVar() {
		return var;
	}

	@TagAttribute(required=true)
	public void setVar(String var) {
		this.var = var;
	}

	public String getFrom() {
		return from;
	}

	@TagAttribute
	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String getType() {
		return "data-result";
	}
	
}
