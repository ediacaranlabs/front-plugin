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
			DataTableTagComponent parent = 
					(DataTableTagComponent) super.getProperty(DataTableTagComponent.DATA_TABLE_PROPERTY);
			
			if(parent == null) {
				throw new IllegalArgumentException("expected parent <data-table/>");
			}
			
			String id = parent.getId();
			return id;
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
