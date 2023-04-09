package br.com.uoutec.community.ediacaran.front.page;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.SearchType;
import br.com.uoutec.community.ediacaran.core.system.i18n.LanguageRegistry;
import br.com.uoutec.community.ediacaran.core.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.front.objects.PagesObjectsManagerDriver;

@Singleton
@Named("editPage")
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

	public ObjectMetadata registerPageByTitle(String path, String title, String locale, Page page) {
		title = normalize(title);
		return registerPage(path + "/" + title, PluginLanguageUtils.toLocale(locale), page);
	}

	public ObjectMetadata registerPageByName(String path, String name, String locale, Page page) {
		name = normalize(name);
		return registerPage(path + "/" + name, PluginLanguageUtils.toLocale(locale), page);
	}
	
	public ObjectMetadata registerPage(String path, String locale, Page page) {
		return registerPage(path, PluginLanguageUtils.toLocale(locale), page);
	}
	
	public ObjectMetadata registerPage(String path, Locale locale, Page page) {
		
		if(page.getBreadcrumb() == null) {
			page.setBreadcrumb(createBreadcrumbPath(path, page));
		}
		
		return objectsManager.registerObject(PagesObjectsManagerDriver.DRIVER_NAME +  path, locale, page);
	}

	public void unregisterPageByName(String path, String name, String locale) {
		name = normalize(name);
		unregisterPage(path + "/" + name, locale);
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
		return objectsManager.getTemplateById(PagesObjectsManagerDriver.DRIVER_NAME, name);
	}

	public Page getPageByName(String path, String name, String locale) {
		name = normalize(name);
		return getPage(path + "/" + name, locale);
	}
	
	public Page getPage(String path, String locale) {
		return getPage(path, PluginLanguageUtils.toLocale(locale));
	}
	
	public Page getPage(String path, Locale locale) {
		return (Page) objectsManager.getObject(PagesObjectsManagerDriver.DRIVER_NAME + path, locale);
	}
	
	public boolean isValidTemplate(String value) {
		return value != null && objectsManager.getTemplateById(PagesObjectsManagerDriver.DRIVER_NAME, value) != null;
	}
	
	private String normalize(String name) {
		return 
			Normalizer.normalize(name, Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.replaceAll("[^a-zA-Z0-9\\s]", " ")
			.toLowerCase()
			.replaceAll("\\s+", "-");
	}

	private List<BreadcrumbPath> createBreadcrumbPath(String path, Page page){
		
		List<BreadcrumbPath> list = new ArrayList<BreadcrumbPath>();
		
		String[] paths = path.split("/+");
		StringBuilder fullPath = new StringBuilder();
		
		list.add(new BreadcrumbPath("Home", "home", "/"));
		for(int i=1;i<paths.length -1;i++) {
			
			String p = paths[i];
			
			fullPath.append("/").append(p);
			String index = fullPath.toString() + "/index";
			
			BreadcrumbPath bcp;
			
			if(getPage(index, (Locale)null) != null) {
				bcp = new BreadcrumbPath(p, null, fullPath.toString());
			}
			else {
				bcp = new BreadcrumbPath(p, null, "#");
			}
			
			list.add(bcp);
		}
		
		return list;
		
	}
}
