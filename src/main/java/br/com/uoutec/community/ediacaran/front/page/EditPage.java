package br.com.uoutec.community.ediacaran.front.page;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.SearchType;
import br.com.uoutec.community.ediacaran.core.system.i18n.LanguageRegistry;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.objects.PagesObjectsManagerDriver;

@Singleton
public class EditPage {

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
			path = PagesObjectsManagerDriver.DRIVER_NAME + basePath.substring(0, lastIndex);
			name = basePath.substring(lastIndex + 1, basePath.length());
			
			name = ".*".concat(Arrays.stream(name.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*"))).concat(".*");
		}
		else {
			path = PagesObjectsManagerDriver.DRIVER_NAME + (basePath == null? "" : basePath);
		}
		
		return objectsManager.listMetadata(path, name, locale, true, SearchType.REGEX);
		
	}

	public void unregisterPage(String page, String locale) {
		unregisterPage(page, PluginLanguageUtils.toLocale(locale));
	}
	
	public void unregisterPage(String page, Locale locale) {
		objectsManager.unregisterObject(PagesObjectsManagerDriver.DRIVER_NAME +  page, locale);
	}
	
	public Map<Locale, String> getSupportedLocales() {
		return languageRegistry.getSupportedLocalesName();
	}
 
	public Map<String,ObjectTemplate> getTemplates(){
		return objectsManager.getTemplatesIdMap(PagesObjectsManagerDriver.DRIVER_NAME);
	}

	public ObjectTemplate getTemplate(String name){
		return objectsManager.getTemplate(PagesObjectsManagerDriver.DRIVER_NAME, name);
	}

	public Page getPage(String path, String locale) {
		return getPage(path, PluginLanguageUtils.toLocale(locale));
	}
	
	public Page getPage(String path, Locale locale) {
		return (Page) objectsManager.getObject(path, locale);
	}
}
