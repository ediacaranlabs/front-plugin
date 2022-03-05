package br.com.uoutec.community.ediacaran.front.components;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.theme.Theme;

public class PropertiesComponentTemplateImp implements PropertiesComponentTemplate{

	private Component component;
	
	private Map<String, Object> properties;

	public PropertiesComponentTemplateImp(Component component, Map<String, Object> properties) {
		super();
		this.component = component;
		this.properties = properties;
	}

	@Override
	public Theme getTheme() {
		return component.getTheme();
	}

	@Override
	public String getPackageTheme() {
		return component.getPackageTheme();
	}

	@Override
	public Object getProperty(String name) {
		return properties.get(name);
	}
	
}