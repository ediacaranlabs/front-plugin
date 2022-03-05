package br.com.uoutec.community.ediacaran.front.tags;

import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="form", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FormTagComponent extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/form";
	
	public static final String FORM = FormTagComponent.class.getSimpleName() + ":form";
	
	public static final String VERTICAL_FORM = FormTagComponent.class.getSimpleName() + ":vertical_form";
	
	public static final String VERTICAL_FORM_VALUE = "true";
	
	/* ------------ Attr ---------------*/
	
	private String acceptCharset;
	
	private String action;
	
	private String enctype;
	
	private String method;
	
	private String target;
	
	private String update;
	
	/* ------------ Prop ---------------*/
	
	//private String style; // inline, horizontal, vertical

	public FormTagComponent() {
	}

    protected Component createComponent() {
    	return new Component() {
    		
    		protected void applyTemplate(String template, Map<String, Object> vars, Writer out){
    	    	Object oldForm = super.setProperty(FORM, FormTagComponent.this);
    			super.applyTemplate(template, vars, out);
    	    	super.setProperty(FORM, oldForm);
    		}
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getAcceptCharset() {
		return acceptCharset;
	}

	@TagAttribute
	public void setAcceptCharset(String acceptCharset) {
		this.acceptCharset = acceptCharset;
	}

	public String getAction() {
		return action;
	}

	@TagAttribute
	public void setAction(String action) {
		this.action = action;
	}

	public String getEnctype() {
		return enctype;
	}

	@TagAttribute
	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getMethod() {
		return method;
	}

	@TagAttribute
	public void setMethod(String method) {
		this.method = method;
	}

	public String getTarget() {
		return target;
	}

	@TagAttribute
	public void setTarget(String target) {
		this.target = target;
	}

	public String getUpdate() {
		return update;
	}

	@TagAttribute
	public void setUpdate(String update) {
		this.update = update;
	}
	
}
