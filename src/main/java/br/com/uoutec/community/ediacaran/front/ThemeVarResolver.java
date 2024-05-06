package br.com.uoutec.community.ediacaran.front;

import br.com.uoutec.community.ediacaran.front.theme.Theme;
import br.com.uoutec.community.ediacaran.front.theme.ThemeRegistry;
import br.com.uoutec.ediacaran.core.VarResolver;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;
import br.com.uoutec.ediacaran.core.plugins.PluginPropertyException;

public class ThemeVarResolver implements VarResolver{

	public ThemeVarResolver() {
	}
	
	@Override
	public Object getValue(Object base, String property) {
		
		try {
			if(base == null) {
				ThemeRegistry themeRegistry = EntityContextPlugin.getEntity(ThemeRegistry.class);
				Theme theme = themeRegistry.getCurrentTheme();
				String packagePath = theme.getTemplate(property);
				return theme.getContext() + ":" + packagePath;
			}
			
			return null;
		}
		catch(PluginPropertyException e) {
			throw e;
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}
	
}
