package br.com.uoutec.community.ediacaran.front.objects;

import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;

public class PageObjectTemplate extends ObjectTemplate{

	private PageObjectTemplateType type;
	
	public PageObjectTemplate(String id, String name, String template, PageObjectTemplateType type) {
		super(id, name, template);
		this.type = type;
	}

	public PageObjectTemplateType getType() {
		return type;
	}
	
}
