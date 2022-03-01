package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.theme.Theme;

public interface ComponentProperties {

	Theme getTheme();
	
	String getPackageTheme();
	
	Object getProperty(String name);
	
}
