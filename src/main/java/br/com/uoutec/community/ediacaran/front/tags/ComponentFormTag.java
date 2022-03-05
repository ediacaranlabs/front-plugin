package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

public abstract class ComponentFormTag extends AbstractSimpleTagComponent {

	public static final String TEMPLATE = "/components/form-group";
	
	public static final String FORM = ComponentFormTag.class.getSimpleName() + ":form";
	
	/* ------------ Attr ---------------*/
	
	private String componentType;
	
	private String name;

	private String value;
	
	private Boolean enabled;

	private Boolean required;
	
	private Boolean readonly;
	
	private String form;
	
	private Boolean group;
	
	public ComponentFormTag() {
	}
	
    protected String getWrapperTemplate() {
    	return TEMPLATE;
    }
    
	public String getName() {
		return name;
	}

	public Boolean getGroup() {
		return group;
	}

	@TagAttribute
	public void setGroup(Boolean group) {
		this.group = group;
	}

	@TagAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	@TagAttribute
	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getRequired() {
		return required;
	}

	@TagAttribute
	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getForm() {
		return form;
	}

	@TagAttribute
	public void setForm(String form) {
		this.form = form;
	}

	public String getComponentType() {
		return componentType;
	}

	@TagAttribute
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	@TagAttribute
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	@TagAttribute
	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

}
