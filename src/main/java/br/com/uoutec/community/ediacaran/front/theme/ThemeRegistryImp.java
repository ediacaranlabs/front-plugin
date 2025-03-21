package br.com.uoutec.community.ediacaran.front.theme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.application.security.RuntimeSecurityPermission;
import br.com.uoutec.ediacaran.core.plugins.PluginType;
import br.com.uoutec.ediacaran.core.plugins.PublicBean;

@Singleton
public class ThemeRegistryImp implements ThemeRegistry, PublicBean{

	private static final Logger logger = LoggerFactory.getLogger(ThemeRegistry.class);
	
	private ConcurrentMap<String, ThemeEntry> themes;
	
	private PluginType pluginData;
	
	public ThemeRegistryImp() {
	}
	
	@Inject
	public ThemeRegistryImp(PluginType pluginData) {
		this.themes = new ConcurrentHashMap<String, ThemeEntry>();
		this.pluginData = pluginData;
	}
	
	public List<String> getThemeNames(){
		return Collections
				.unmodifiableList(
						themes.keySet().stream().collect(Collectors.toList()));
	}
	
	@Override
	public synchronized void registerTheme(String name, String context, String template) throws ThemeException{
		
		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX + name + ".register"));
		
		ThemeEntry entry = themes.get(name);
		
		if(entry != null) {
			throw new ThemeException("theme has been added: " + name);
			
		}	

		entry = new ThemeEntry();
		entry.name = name;
		entry.context = context;
		entry.packages = new ConcurrentHashMap<String, ThemePackage>();
		entry.tema = new ThemeImp(name, context, "/templates" + template, entry.packages);
		
		if(logger.isTraceEnabled()) {
			logger.trace("thema created: {}[template={}, context={}]", new Object[] {name, template, context});
		}
		
		themes.put(name, entry);
		
		
	}

	@Override
	public void unregisterTheme(String name) {

		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX + name + ".unregister"));
		
		if(themes.remove(name) != null) {
			if(logger.isWarnEnabled()) {
				logger.warn("theme {} removed", name);
			}
		}
		else
		if(logger.isTraceEnabled()) {
			logger.trace("theme {} not found", name);
		}
		
	}
	
	@Override
	public synchronized void registerPackageTheme(String name, String packageName, String template) throws ThemeException{

		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX + name + ".package.register"));
		
		ThemeEntry entry = themes.get(name);
		
		if(entry == null) {
			throw new ThemeException("theme not found: " + name);
		}
		
		if(packageName == null) {
			throw new ThemeException("theme package not found: " + name + "/" + packageName);
		}
			
		if(entry.packages.containsKey(packageName)) {
			throw new ThemeException("theme package has been added: " + name + "/" + packageName);
		}
		
		ThemePackage temaPackage = new ThemePackage(
				packageName, 
				template, 
				new ConcurrentHashMap<String, TemplateComponent>(),
				new ConcurrentHashMap<String, List<PublicResource>>());
		
		entry.packages.put(packageName, temaPackage);
		
		if(logger.isTraceEnabled()) {
			logger.trace("package added: {}[theme={}, template={}]", new Object[] {packageName, name, template});
		}
			
		
	}

	@Override
	public synchronized void unregisterPackageTheme(String name, String packageName) {

		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX + name + ".package.register"));
		
		if(packageName == null) {
			throw new ThemeException("theme package not found: " + name + "/" + packageName);
		}
		
		ThemeEntry entry = themes.get(name);
		
		if(entry == null) {
			return;
		}
		
			
		boolean removed = entry.packages.remove(packageName) != null;
		
		if(logger.isTraceEnabled()) {
			logger.trace("{} package: {}[theme={}, template={}]", new Object[] {removed? "removed" : "not found", packageName, name});
		}
			
		
	}
	
	@Override
	public synchronized void registerTemplateComponent(String name, String packageName, String template, TemplateComponent tagTemplate) throws ThemeException{

		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX + name + ".component.register"));

		ThemeEntry entry = themes.get(name);
		
		if(entry == null) {
			throw new ThemeException("theme not found: " + name);
		}
		
		ThemePackage temaPackage = entry.packages.get(packageName);
		
		if(temaPackage == null) {
			throw new ThemeException("theme package not found: " + name + "/" + packageName);
		}
		
		ConcurrentMap<String, TemplateComponent> tagTemplates = temaPackage.getTagTemplates();
		
		if(tagTemplates.put(template, tagTemplate) == null){
			
			if(logger.isTraceEnabled()) {
				logger.trace("added component template: {}[template={}, package={}]", new Object[] {name, template, packageName});
			}
			
		}
		else
		if(logger.isTraceEnabled()) {
			logger.trace("overridden component template: {}[template={}, package={}]", new Object[] {name, template, packageName});
		}
		
	}

	@Override
	public synchronized void unregisterTemplateComponent(String name, String packageName, String template, TemplateComponent tagTemplate) {

		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX +  name + ".component.unregister"));

		ThemeEntry entry = themes.get(name);
		
		if(entry == null) {
			return;
		}
		
		ThemePackage temaPackage = entry.packages.get(packageName);
		
		if(temaPackage == null) {
			return;
		}
		
		ConcurrentMap<String, TemplateComponent> tagTemplates = temaPackage.getTagTemplates();
		
		boolean removed = tagTemplates.remove(template) != null;
		
		if(logger.isTraceEnabled()) {
			logger.trace("{} template component: {}[template={}, package={}]", new Object[] {removed? "removed": "fot found",name, template, packageName});
		}
		
	}	
	@Override
	public synchronized void registerResource(String name, String packageName, String resource, String type, String path) throws ThemeException{

		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX + name + ".resource.register"));

		ThemeEntry entry = themes.get(name);
		
		if(entry == null) {
			throw new ThemeException("theme not found: " + name);
		}
		
		ThemePackage temaPackage = entry.packages.get(packageName);
		
		if(temaPackage == null) {
			throw new ThemeException("theme package not found: " + name + "/" + packageName);
		}
		
		ConcurrentMap<String, List<PublicResource>> map = temaPackage.getResources();
		
		List<PublicResource> resources = map.get(type);
		
		if(resources == null) {
			resources = new ArrayList<PublicResource>();
			map.put(type, resources);
		}

		if(!path.startsWith("/") && !path.toLowerCase().matches("^http?(s):\\/\\/.*")) {
			path = entry.tema.getContext() + "/templates" + "/" + path;
		}
		
		PublicResource pr = new PublicResource(resource, path);
		int index;
		
		if((index = resources.indexOf(pr)) >= 0){
			
			resources.set(index, pr);
			
			if(logger.isTraceEnabled()) {
				logger.trace("overridden resource: {}[path={}, resource={}, package={}]", new Object[] {name, path, resource, packageName});
			}
			
		}
		else {
			
			resources.add(pr);
			
			if(logger.isTraceEnabled()) {
				logger.trace("added resource: {}[resource={}, resource={}, package={}]", new Object[] {name, path, resource, packageName});
			}
			
		}
		
	}

	@Override
	public synchronized void unregisterResource(String name, String packageName, String resource, String type, String path) {

		ContextSystemSecurityCheck.checkPermission(new RuntimeSecurityPermission(PERMISSION_PREFIX + name + ".resource.unregister"));

		ThemeEntry entry = themes.get(name);
		
		if(entry == null) {
			return;
		}
		
		ThemePackage temaPackage = entry.packages.get(packageName);
		
		if(temaPackage == null) {
			return;
		}
		
		ConcurrentMap<String, List<PublicResource>> map = temaPackage.getResources();
		
		List<PublicResource> resources = map.get(type);
		
		if(resources == null) {
			return;
		}

		if(!path.startsWith("/") && !path.toLowerCase().matches("^http?(s):\\/\\/.*")) {
			path = entry.tema.getContext() + "/" + path;
		}
		
		PublicResource pr = new PublicResource(resource, null);
		
		boolean removed = resources.remove(pr);
			
		if(logger.isTraceEnabled()) {
			logger.trace("{} resource: {}[path={}, resource={}, package={}]", new Object[] {removed? "removed" : "not found", name, path, resource, packageName});
		}
		
	}
	
	@Override
	public Theme getCurrentTheme() {
		return getTheme(pluginData.getConfiguration().getString("theme"));
	}
	
	@Override
	public Theme getTheme(String name) {
		
		ThemeEntry entry = themes.get(name);
		
		if(entry == null) {
			throw new ThemeException("theme not found: " + name);
		}
		
		return entry.tema;
	}

	@SuppressWarnings("unused")
	private static class ThemeEntry {
		
		public String name;
		
		public String context;
		
		public ConcurrentMap<String, ThemePackage> packages;
		
		public Theme tema;
		
	}

}
