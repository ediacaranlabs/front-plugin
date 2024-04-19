package br.com.uoutec.community.ediacaran.front.tags;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.FieldValidatorComponent;
import br.com.uoutec.community.ediacaran.front.components.ValidatorEntity;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="field-validator", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class FieldValidatorTagComponent extends AbstractBodyTagComponent {

	private static final long serialVersionUID = 748182107582888257L;

	private String form;
	
	private String field;
	
	private Set<ValidatorEntity> validators;
	
	public FieldValidatorTagComponent() {
	}
	
    protected Component createComponent() {
    	return new FieldValidatorComponent();
    }
	
    @Override
    public int doStartTag() throws JspException {
		this.validators = new HashSet<>();
    	super.pageContext.setAttribute(FieldValidatorTagComponent.class.getName(), this);
        return super.doStartTag();
    }

    public void addValidator(ValidatorEntity value) {
    	this.validators.add(value);
    }
    
    @Override
    public int doEndTag() throws JspException {
        int result = super.doEndTag();
        super.pageContext.setAttribute(FieldValidatorTagComponent.class.getName(), null);
        this.validators.clear();
        return result;
    }
    
    public String getForm() {
		return form;
	}

    @TagAttribute
	public void setForm(String form) {
		this.form = form;
	}

	public String getField() {
		return field;
	}

    @TagAttribute
	public void setField(String field) {
		this.field = field;
	}
    
	@Override
	public String getType() {
		return "field-validator";
	}
    
}
