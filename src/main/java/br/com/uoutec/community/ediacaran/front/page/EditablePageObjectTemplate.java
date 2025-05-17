package br.com.uoutec.community.ediacaran.front.page;

import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;

public class EditablePageObjectTemplate extends ObjectTemplate{

	private String editTemplate;
	
	public EditablePageObjectTemplate(String id, String name, String template, String editTemplate) {
		super(id, name, template);
	}

	public String getEditTemplate() {
		return editTemplate;
	}

	public void setEditTemplate(String editTemplate) {
		this.editTemplate = editTemplate;
	}
	
}
