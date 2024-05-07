package br.com.uoutec.community.ediacaran.front;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.theme.ThemeRegistry;
import br.com.uoutec.ediacaran.core.plugins.PluginOptionsResolver;
import br.com.uoutec.ediacaran.core.plugins.PluginPropertyOption;

@Singleton
public class ThemePluginOptionsResolver 
	implements PluginOptionsResolver{

	@Inject
	private ThemeRegistry themeRegistry;
	
	@Override
	public List<PluginPropertyOption> getOptions() {
		
		List<String> list = themeRegistry.getThemeNames();
		
		List<PluginPropertyOption> result = new ArrayList<>();
		
		for(String r: list) {
			result.add(new PluginPropertyOption(r, r));
		}
		
		return result;
	}

}
