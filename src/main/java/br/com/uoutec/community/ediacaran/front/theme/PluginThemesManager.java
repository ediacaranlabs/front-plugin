package br.com.uoutec.community.ediacaran.front.theme;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import br.com.uoutec.application.ClassUtil;
import br.com.uoutec.application.io.Path;
import br.com.uoutec.application.io.VfsException;
import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;
import br.com.uoutec.ediacaran.core.plugins.PluginConfigurationMetadata;
import br.com.uoutec.ediacaran.core.plugins.PluginPath;
import br.com.uoutec.ediacaran.core.plugins.PluginType;
import br.com.uoutec.ediacaran.web.WebUtil;

public class PluginThemesManager {

	@SuppressWarnings("unchecked")
	public void registerThemes() throws VfsException, IOException, ReflectiveOperationException {
		
		PluginType pluginType = EntityContextPlugin.getEntity(PluginType.class);
		ThemeRegistry themeRegistry = EntityContextPlugin.getEntity(ThemeRegistry.class);
		
		PluginConfigurationMetadata pmd = pluginType.getConfiguration().getMetadata();
		
		PluginPath pp = pmd.getPath();
		Path base = pp.getBase();
		base = base.getPath("themes");
		Path packages = base.getPath("themes.properties");
		packages = packages.getAbsolutePath();
		
		if(packages.exists() && !packages.isDirectory()) {
			Properties p = new Properties();
			
			try (InputStream i = packages.openInputStream()){
				p.load(i);
			}
			
			Enumeration<String> names = (Enumeration<String>) p.propertyNames();
			
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				String value = p.getProperty(name);
				
				String[] path = name.split("/");
				if(path.length == 1) {
					themeRegistry.registerTheme(path[0], WebUtil.getPath(pmd), value);
				}
				
			}

			names = (Enumeration<String>) p.propertyNames();
			
			while(names.hasMoreElements()) {
				
				String name = names.nextElement();
				String value = p.getProperty(name);
				
				String[] path = name.split("/");
				
				if(path.length == 2) {
					themeRegistry.registerPackageTheme(path[0], path[1], value);
					
				}
			}
			
			
			names = (Enumeration<String>) p.propertyNames();

			while(names.hasMoreElements()) {
				
				String name = names.nextElement();
				String value = p.getProperty(name);
				
				String[] path = name.split("/");
				
				if(path.length == 3) {
					
					if("resources".equals(path[2])) {
						String[] resources = value.split("\\,");
						
						for(String resource: resources) {
							String rValue = p.getProperty(path[0] + "/" + path[1] + "/resources" + resource);
							
							if(rValue == null) {
								throw new ThemeException("resource not found: " + resource);
							}

							String[] tmp = resource.split("/");
							String resourceName = String.join("/", Arrays.copyOf(tmp, tmp.length - 1));
							String type = tmp[tmp.length - 1];
							themeRegistry.registerResource(path[0], path[1], resourceName, type, rValue);
							
						}
					}
					
				}
			}

			names = (Enumeration<String>) p.propertyNames();

			while(names.hasMoreElements()) {
				
				String name = names.nextElement();
				String value = p.getProperty(name);
				
				String[] path = name.split("/");
				
				if(path.length > 3) {
					
					if("tags".equals(path[2])) {
						String[] tmp = Arrays.copyOfRange(path, 3, path.length);
						String template = "/" + String.join("/", tmp);
						
						TemplateComponent c = (TemplateComponent)ContextSystemSecurityCheck.doPrivileged(()->{
							try {
								return ClassUtil.getInstance(value);
							}
							catch(Throwable e) {
								throw new RuntimeException(e);
							}
						});
						
						c.loadConfiguration();
						c.loadTemplate();
						themeRegistry.registerTemplateComponent(path[0], path[1], template, c);
					}
					
				}
			}			
		}
				
	}

	public void unregisterThemes() {
		
	}
	
}
