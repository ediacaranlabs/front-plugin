package br.com.uoutec.community.ediacaran.front.theme;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.uoutec.community.ediacaran.front.components.Component;

public class ThemeImp implements Theme {

	private static final Logger logger = LoggerFactory.getLogger(Theme.class);
	
	private String name;
	
	private String path;
	
	private ThemeImp parent;
	
	private ConcurrentMap<String, ThemePackage> packages;
	

	public ThemeImp(String name, String path, ThemeImp parent, ConcurrentMap<String, ThemePackage> packages) {
		this.name = name;
		this.packages = packages;
		this.path = path;
		this.parent = parent;
	}

	@Override
	public void buildComponent(String template, String packageName, ComponentVars componentVars, Map<String, Object> extVars, Writer out) throws ThemeException {
		
		TemplateComponent parentTemplateComponent = parent == null? null : getTemplateComponent(parent, template, packageName);
		TemplateComponent templateComponent       = getTemplateComponent(this, template, packageName);

		if(parentTemplateComponent == null && templateComponent == null) {
			throw new ThemeException("template not found: " + template);
		}
		
		TemplateComponent p = templateComponent == null? parentTemplateComponent : templateComponent;
		
		Map<String, Object> vars = new HashMap<String, Object>();

		try {
			vars.putAll(
				componentVars.prepareVars(
					p.getPropertiesParser(), p.getProperties(), 
					p.getAttributesParser(), 
					p.getEmptyAttributes(), 
					p.getAttributes()
				)
			);
		}
		catch(Throwable ex) {
			throw new ThemeException("unable to get tag properties: " + template + "[package=" + packageName + "]", ex);
		}
			
			
		if(extVars != null) {
			vars.putAll(extVars);
		}

		try {
			if(componentVars instanceof Component) {
				Component c = (Component)componentVars;
				c.registerFrontID(out);
			}
			p.build(vars, out);
		}
		catch(ThemeException ex) {
			throw ex;
		}
		catch(Throwable ex) {
			throw new ThemeException("unable to load template tag: " + template + "[package=" + packageName + "]", ex);
		}
	}

	@Override
	public void buildComponent(String template, String packageName, Writer out, Object... vars) throws ThemeException {
		TemplateComponent parentTemplateComponent = parent == null? null : getTemplateComponent(parent, template, packageName);
		TemplateComponent templateComponent       = getTemplateComponent(this, template, packageName);
		
		if(templateComponent != null) {
			templateComponent.build(out, vars);
		}
		else
		if(parentTemplateComponent != null) {
			parentTemplateComponent.build(out, vars);
		}
		else {
			throw new ThemeException("template not found: " + template);
		}
	}

	private TemplateComponent getTemplateComponent(ThemeImp theme, String template, String packageName) throws ThemeException {
		
		ThemePackage temaPackage = theme.getPackage(packageName);
		ConcurrentMap<String, TemplateComponent> tagTemplates = temaPackage.getTagTemplates();
		
		if(tagTemplates == null) {
			
			if(temaPackage.getName().equals("front")) {
				return null;
			}

			temaPackage = theme.getPackage(null);
			tagTemplates = temaPackage.getTagTemplates();
			return tagTemplates.get(template);
		}
		
		return tagTemplates.get(template);
	}
	
	@Override
	public String getBasePath() {
		return path;
	}
	
	@Override
	public String getTemplate(String name) {
		String parentTemaPackage = parent == null? null : getTemplate(parent, name);
		String temaPackage = parentTemaPackage == null? getTemplate(this, name) : getRequiredTemplate(parent, name);
		return temaPackage == null? parentTemaPackage : temaPackage;
	}

	private String getTemplate(ThemeImp theme, String name) {
		ThemePackage temaPackage = theme.getPackage(name);
		return temaPackage.getThemePath() + temaPackage.getPath();
	}

	private String getRequiredTemplate(ThemeImp theme, String name) {
		ThemePackage temaPackage = theme.getPackage(name);
		return name == null || temaPackage.getName().equals(name)? temaPackage.getThemePath() + temaPackage.getPath() : null;
	}
	
	@Override
	public String getContext(String packageName) {
		String parentContext = parent == null? null : getContext(parent, packageName);
		String context = parentContext == null? getContext(this, packageName) : getRequiredContext(this, packageName);
		return context == null? parentContext : context;
	}

	private String getContext(ThemeImp theme, String name) {
		ThemePackage temaPackage = theme.getPackage(name);
		return temaPackage.getContext();
	}

	private String getRequiredContext(ThemeImp theme, String name) {
		ThemePackage temaPackage = theme.getPackage(name);
		return name == null || temaPackage.getName().equals(name)? temaPackage.getContext() : null;
	}
	
	private ThemePackage getPackage(String name) throws ThemeException {
		ThemePackage parentThemePackage = parent == null? null : getPackage(parent, name);
		ThemePackage themePackage = getPackage(this, name);
		
		if(themePackage == null && parentThemePackage == null) {
			throw new ThemeException("theme package not found: " + name);
		}
		
		ConcurrentMap<String, TemplateComponent> tagTemplates = new ConcurrentHashMap<>();
		ConcurrentMap<String, List<PublicResource>> resources = new ConcurrentHashMap<>();
		String themePath = null;
		String path = null;
		String context = null;
		
		if(parentThemePackage != null) {
			tagTemplates.putAll(parentThemePackage.getTagTemplates());
			resources.putAll(parentThemePackage.getResources());
			themePath = parentThemePackage.getThemePath();
			path = parentThemePackage.getPath();
			name = parentThemePackage.getName();
			context = parentThemePackage.getContext();
		}
		
		if(themePackage != null) {
			tagTemplates.putAll(themePackage.getTagTemplates());
			resources.putAll(themePackage.getResources());
			themePath = themePackage.getThemePath();
			path = themePackage.getPath();
			name = themePackage.getName();
			context = themePackage.getContext();
		}
		
		return new ThemePackage(name, context, themePath, path, tagTemplates, resources);
	}

	private ThemePackage getPackage(ThemeImp theme, String name) throws ThemeException {
		
		if(name == null) {
			name = "front";
		}
		
		ThemePackage temaPackage = theme.packages.get(name);
		
		if(temaPackage == null) {
			temaPackage = theme.packages.get("front");
		}
		
		return temaPackage;
	}
	
	@Override
	public List<PublicResource> getResourcesByType(String type, String packageName) {
		
		List<PublicResource> parentResourcesByType = parent == null? null : getResourcesByType(parent, type, packageName);
		List<PublicResource> resourcesByType = getResourcesByType(this, type, packageName);
		
		Map<PublicResource, Integer> index = new HashMap<>();
		List<PublicResource> result = new ArrayList<>();

		if(parentResourcesByType != null) {
			for(PublicResource r: parentResourcesByType) {
				index.put(r, result.size());
				result.add(r);
			}
		}

		if(resourcesByType != null) {
			for(PublicResource r: resourcesByType) {
				Integer i = index.get(r);
				
				if(i != null) {
					result.set(i, r);
				}
				else {
					index.put(r, result.size());
					result.add(r);
				}
			}
		}
		
		return result;
	}

	private List<PublicResource> getResourcesByType(ThemeImp theme, String type, String packageName) {
		
		ThemePackage temaPackage = theme.getPackage(packageName);
		
		List<PublicResource> resourcesType = temaPackage.getResources().get(type);
		
		if(resourcesType == null && logger.isTraceEnabled()) {
			logger.trace("resource type {} not found [package={}, theme={}]", new Object[] {type, packageName, name});
		}
		
		return resourcesType;
	}

}
