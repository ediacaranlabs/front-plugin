package br.com.uoutec.community.ediacaran.front.page;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.system.i18n.I18nRegistry;
import br.com.uoutec.community.ediacaran.system.i18n.PluginLanguageUtils;
import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;
import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsTemplateManager;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsTemplateManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.SearchType;

@Singleton
@Named("editPage")
public class PageManager {

	@Inject
	public ObjectsTemplateManager objectsManager;

	@Inject
	private I18nRegistry languageRegistry;
	
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
		else{
			if(basePath != null) {
				name = ".*".concat(Arrays.stream(basePath.split("\\*+")).map(e->Pattern.quote(e)).collect(Collectors.joining(".*"))).concat(".*");
			}
			path = PagesObjectsManagerDriver.DRIVER_NAME;
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
	
	public void unregisterPage(String path, String id, String locale) {
		unregisterPage(path + "/" + id, PluginLanguageUtils.toLocale(locale));
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
 
	public Map<String,ObjectTemplate> getTemplates(PageObjectTemplateType type){
		return objectsManager.getTemplatesIdMap(PagesObjectsManagerDriver.DRIVER_NAME)
				.entrySet().stream()
				.filter((r)->((PageObjectTemplate)r.getValue()).getType() == type)
				.collect(Collectors.toMap((e)->e.getKey().split("\\;")[0], Entry::getValue));
	}

	public void registerTemplate(String id, String name, String template, PageObjectTemplateType type){
		
		ObjectsTemplateManagerDriver driver = 
				(ObjectsTemplateManagerDriver) objectsManager.getDriver(PagesObjectsManagerDriver.DRIVER_NAME);
		
		driver.registerTemplate( 
				new PageObjectTemplate(
						name + ";" + type.name().toLowerCase(), 
						name, 
						template,
						type
				)
		);

	}

	public void unregisterTemplate(String id, PageObjectTemplateType type){
		ObjectsTemplateManagerDriver driver = 
				(ObjectsTemplateManagerDriver) objectsManager.getDriver(PagesObjectsManagerDriver.DRIVER_NAME);
		
		driver.unregisterTemplate(id + ";" + type.name().toLowerCase());
	}
	
	public ObjectTemplate getTemplate(String name, PageObjectTemplateType type){
		return objectsManager.getTemplateById(PagesObjectsManagerDriver.DRIVER_NAME, name + ";" + type.name().toLowerCase());
	}

	public Page getPageByName(String path, String name, String locale) {
		name = normalize(name);
		return getPage(path + "/" + name, locale);
	}

	public Page getPageById(String path, String id, String locale) {
		return getPage(path + "/" + id, locale);
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
