package br.com.uoutec.community.ediacaran.front.page;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.core.system.i18n.LanguageRegistry;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.objects.MenubarObjectsManagerDriver;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.SearchType;
import br.com.uoutec.community.ediacaran.front.pub.MenuBar;

@Singleton
@Named("editMenubar")
public class EditMenubar {

	@Inject
	public ObjectsTemplateManager objectsManager;

	@Inject
	private LanguageRegistry languageRegistry;
	
	public List<ObjectMetadata> list(String basePath, String locale){
		return list(basePath, PluginLanguageUtils.toLocale(locale));
	}
	
	public List<ObjectMetadata> list(String basePath, Locale locale){
		
		String path = null;
		String name = null;

		basePath      = basePath == null? null : basePath.replaceAll("/+", "/");
		int lastIndex = basePath == null? -1 : basePath.lastIndexOf("/");
		
		if(lastIndex > 0 ) {
			path = MenubarObjectsManagerDriver.DRIVER_NAME + basePath.substring(0, lastIndex);
			name = basePath.substring(lastIndex + 1, basePath.length());
			
			name = ".*".concat(Arrays.stream(name.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*"))).concat(".*");
		}
		else {
			path = MenubarObjectsManagerDriver.DRIVER_NAME + (basePath == null? "" : basePath);
		}
		
		return objectsManager.listMetadata(path, name, locale, true, SearchType.REGEX);
		
	}

	public ObjectMetadata registerMenubarByName(String path, String name, String locale, MenuBar page) {
		name = normalize(name);
		return registerMenubar(path + "/" + name, PluginLanguageUtils.toLocale(locale), page);
	}

	public ObjectMetadata registerMenubarById(String path, String id, String locale, MenuBar page) {
		return registerMenubar(path + "/" + id, PluginLanguageUtils.toLocale(locale), page);
	}
	
	public ObjectMetadata registerMenubar(String path, String locale, MenuBar page) {
		return registerMenubar(path, PluginLanguageUtils.toLocale(locale), page);
	}
	
	public ObjectMetadata registerMenubar(String path, Locale locale, MenuBar page) {
		return objectsManager.registerObject(MenubarObjectsManagerDriver.DRIVER_NAME +  path, locale, page);
	}

	public void unregisterMenubarByName(String path, String name, String locale) {
		name = normalize(name);
		unregisterMenubar(path + "/" + name, locale);
	}
	
	public void unregisterMenubar(String page, String locale) {
		unregisterMenubar(page, PluginLanguageUtils.toLocale(locale));
	}
	
	public void unregisterMenubar(String page, Locale locale) {
		objectsManager.unregisterObject(MenubarObjectsManagerDriver.DRIVER_NAME +  page, locale);
	}
	
	public Map<Locale, String> getSupportedLocales() {
		return languageRegistry.getSupportedLocalesName();
	}
 
	public MenuBar getMenubarByName(String path, String name, String locale) {
		name = normalize(name);
		return getMenubar(path + "/" + name, locale);
	}
	
	public MenuBar getMenubar(String path, String locale) {
		return getMenubar(path, PluginLanguageUtils.toLocale(locale));
	}
	
	public MenuBar getMenubar(String path, Locale locale) {
		return (MenuBar) objectsManager.getObject(MenubarObjectsManagerDriver.DRIVER_NAME + path, locale);
	}
	
	private String normalize(String name) {
		return 
			Normalizer.normalize(name, Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.replaceAll("[^a-zA-Z0-9\\s]", " ")
			.toLowerCase()
			.replaceAll("\\s+", "-");
	}

}
