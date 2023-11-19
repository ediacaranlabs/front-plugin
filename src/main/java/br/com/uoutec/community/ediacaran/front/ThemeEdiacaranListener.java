package br.com.uoutec.community.ediacaran.front;

import java.io.File;
import java.io.InputStream;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import javax.inject.Singleton;

import org.brandao.brutos.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.uoutec.community.ediacaran.EdiacaranEventListener;
import br.com.uoutec.community.ediacaran.EdiacaranEventObject;
import br.com.uoutec.community.ediacaran.front.theme.TemplateComponent;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;
import br.com.uoutec.community.ediacaran.front.theme.ThemeRegistry;
import br.com.uoutec.community.ediacaran.io.FileSystem;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginConfigurationMetadata;
import br.com.uoutec.community.ediacaran.plugins.PluginInitializer;
import br.com.uoutec.community.ediacaran.plugins.PluginNode;
import br.com.uoutec.community.ediacaran.plugins.PluginPath;
import br.com.uoutec.community.ediacaran.plugins.PluginType;
import br.com.uoutec.community.ediacaran.web.WebUtil;

@Singleton
public class ThemeEdiacaranListener implements EdiacaranEventListener{

	private static final Logger logger = LoggerFactory.getLogger(ThemeEdiacaranListener.class);
	
	@Override
	public void onEvent(EdiacaranEventObject event) {

		if(event.getSource() instanceof PluginInitializer) {
			
			if("installed".equals(event.getType())){
				startContext((PluginNode)event.getData());
			}
			else
			if("uninstalled".equals(event.getType())){
				stopContext((PluginNode)event.getData());
			}
			
		}
		
	}

	private void startContext(PluginNode node) {
		try {
			loadThemes();
		}
		catch(AccessControlException ex) {
			logger.warn("don't have permission to load theme", ex);
		}
		catch(Throwable ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private void stopContext(PluginNode node) {
	}
	
	@SuppressWarnings("unchecked")
	protected void loadThemes() throws Throwable {
		
		PluginType pluginType = EntityContextPlugin.getEntity(PluginType.class);
		ThemeRegistry themeRegistry = EntityContextPlugin.getEntity(ThemeRegistry.class);
		
		PluginConfigurationMetadata pmd = pluginType.getConfiguration().getMetadata();
		
		PluginPath pp = pmd.getPath();
		File base = pp.getBase();
		base = new File(base, "themes");
		File packages = new File(base, "themes.properties");
		packages = packages.getAbsoluteFile();
		
		if(packages.exists() && !packages.isDirectory()) {
			Properties p = new Properties();
			
			try (InputStream i = FileSystem.getInputStream(packages)){
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
						
						final ReflectiveOperationException[] doException = new ReflectiveOperationException[1];
						TemplateComponent c = (TemplateComponent)
								AccessController.doPrivileged((PrivilegedAction<Object>)()->{
									try {
										return ClassUtil.getInstance(value);
									} 
									catch (ReflectiveOperationException e) {
										doException[0] = e;
										return null;
									}
								});
						
						if(doException[0] != null) {
							throw doException[0];
						}
						
						c.loadConfiguration();
						c.loadTemplate();
						themeRegistry.registerTemplateComponent(path[0], path[1], template, c);
					}
					
				}
			}			
		}
		
	}
	
}
