package br.com.uoutec.community.ediacaran.front.tags;

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

	public static final String TEMPLATE = "/components/data-result";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String var;
	
	private String from;
	
	public DataResultTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
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
		if(from == null) {
	    	Object parent = getParentTag();
			if(parent instanceof DataTableTagComponent) {
				String id = ((DataTableTagComponent)parent).getId();
				return id;
			}
			else {
				throw new IllegalArgumentException("expected parent <data-table/>");
			}
		}
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
