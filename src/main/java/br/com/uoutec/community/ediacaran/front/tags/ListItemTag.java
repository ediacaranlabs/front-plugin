package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="list-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class ListItemTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/list-item";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Boolean inline;
	
	public ListItemTag() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			ListTag list = (ListTag) getProperty(ListTag.PARENT);
    			inline  = "inline".equals(list.getStyle());
    		}
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Boolean getInline() {
		return inline;
	}

	@TagAttribute
	public void setInline(Boolean inline) {
		this.inline = inline;
	}

}
