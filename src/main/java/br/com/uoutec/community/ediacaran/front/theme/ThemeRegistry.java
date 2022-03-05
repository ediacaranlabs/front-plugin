package br.com.uoutec.community.ediacaran.front.theme;

import br.com.uoutec.community.ediacaran.plugins.PublicBean;

public interface ThemeRegistry extends PublicBean{

	public static final String PERMISSION_PREFIX = "app.theme.";
	
	void registerTheme(String name, String context, String template) throws ThemeException;
	
	void registerPackageTheme(String name, String packageName, String template) throws ThemeException;
	
	void unregisterPackageTheme(String name, String packageName);
	
	void registerTemplateComponent(String name, String packageName, String template, TemplateComponent tagTemplate) throws ThemeException;
	
	void unregisterTemplateComponent(String name, String packageName, String template, TemplateComponent tagTemplate);
	
	void registerResource(String name, String packageName, String resource, String type, String path) throws ThemeException;
	
	void unregisterResource(String name, String packageName, String resource, String type, String path);
	
	Theme getCurrentTheme();
	
	Theme getTheme(String name);
	
	void unregisterTheme(String name);
	
}
