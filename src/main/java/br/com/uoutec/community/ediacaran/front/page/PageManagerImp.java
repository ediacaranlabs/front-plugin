package br.com.uoutec.community.ediacaran.front.page;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.objects.FileManager;
import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;
import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileValue;
import br.com.uoutec.community.ediacaran.front.objects.FileManager.Filter;
import br.com.uoutec.community.ediacaran.front.page.PageTemplateManager.PageTemplate;
import br.com.uoutec.community.ediacaran.plugins.PluginType;

@Singleton
public class PageManagerImp implements PageManager {

	public static final String PUBLIC_PATH = "/public";

	private FileManager fileManager;
	
	private PageTemplateManager pageTemplateManager;
	
	@Inject
	public PageManagerImp(PluginType pluginType, PageTemplateManager pageTemplateManager) {
		//PluginConfigurationMetadata pcm = pluginType.getConfiguration().getMetadata();
		//PluginPath pp = pcm.getPath();
		//this.fileManager = new FileManager(new File(pp.getBase(), PUBLIC_PATH), new PageFileManagerHandler());
		String path = System.getProperty("app.web");
		this.fileManager = new FileManager(new File(path), new PageFileManagerHandler());
		this.pageTemplateManager = pageTemplateManager;
	}
	
	@Override
	public PageMetadata registerPage(String path, String name, Locale locale, Page page) throws PageManagerException {
		
		Map<String,Object> ext = new HashMap<String, Object>();
		ext.put("locale", locale);
		FileMetadata fmd = new FileMetadata(path, normalize(name), "pag", ext);
		
		try {
			fileManager.persist(fmd, page);
			return new PageMetadataImp(fmd);
		}
		catch(IOException ex) {
			throw new PageManagerException(ex);
		}
		
	}

	public PageMetadata registerPageIfNotExist(String path, String name, Locale locale, Page page) throws PageManagerException{

		Map<String,Object> ext = new HashMap<String, Object>();
		ext.put("locale", locale);
		FileMetadata fmd = new FileMetadata(path, normalize(name), "pag", ext);
		
		try {
			FileValue fv = fileManager.get(fmd);
			
			if(fv != null) {
				throw new PageExistsException();
			}
			
			fileManager.persist(fmd, page);
			return new PageMetadataImp(fmd);
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public PageMetadata registerPageIfExist(String path, String name, Locale locale, Page page) throws PageManagerException{
		
		Map<String,Object> ext = new HashMap<String, Object>();
		ext.put("locale", locale);
		FileMetadata fmd = new FileMetadata(path, normalize(name), "pag", ext);
		
		try {
			FileValue fv = fileManager.get(fmd);
			
			if(fv == null) {
				throw new PageNotFoundException();
			}
			
			fileManager.persist(fmd, page);
			return new PageMetadataImp(fmd);
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void unregisterPage(String path, String name, Locale locale) throws PageManagerException {

		Map<String,Object> ext = new HashMap<String, Object>();
		ext.put("locale", locale);
		FileMetadata fmd = new FileMetadata(path, normalize(name), "pag", ext);
		
		try {
			fileManager.delete(fmd);
		}
		catch(IOException ex) {
			throw new PageManagerException(ex);
		}
		
	}

	@Override
	public Page getPage(String path, Locale locale) {
		
		int lastIndex = path.lastIndexOf("/");
		
		FileMetadata fmd;

		Map<String,Object> ext = new HashMap<String, Object>();
		ext.put("locale", locale);
		
		if(lastIndex == -1) {
			fmd = new FileMetadata("/", path, "pag", ext);
		}
		else {
			String p = path.substring(0, lastIndex);
			String n = path.substring(lastIndex + 1, path.length());
			
			fmd = new FileMetadata(p, n, "pag", ext);
		}
		
		try {
			FileValue fv = fmd == null? null : fileManager.get(fmd);
			return fv == null? null : (Page)fv.getObject();
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Page getPage(PageMetadata value) {
		try {
			Map<String,Object> ext = new HashMap<String, Object>();
			ext.put("locale", value.getLocale());
			
			FileMetadata fmd = new FileMetadata(value.getPath(), value.getId(), "pag", ext);
			FileValue fv = fileManager.get(fmd);
			return fv == null? null : (Page)fv.getObject();
		}
		catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PageMetadata unique(){
		return unique(null, null);
	}
	
	public PageMetadata unique(Filter filter){
		return unique(null, true, filter);
	}

	public PageMetadata unique(String path, Filter filter){
		return unique(path, true, filter);
	}
	
	public PageMetadata unique(String path, boolean recursive, Filter filter){
		
		List<PageMetadata> list = list(path, recursive, filter);
		
		if(list.size() > 1) {
			throw new IllegalStateException("found: " + list.size());
		}
		
		return list.isEmpty()? null : list.get(0);
		
	}
	
	public List<PageMetadata> list(){
		return list(null);
	}
	
	public List<PageMetadata> list(Filter filter){
		return list(null, true, filter);
	}

	public List<PageMetadata> list(String path, Filter filter){
		return list(path, true, filter);
	}
	
	public List<PageMetadata> list(String path, boolean recursive, Filter filter){
		List<PageMetadata> list = new LinkedList<PageMetadata>();
		list(list, path, filter, recursive);
		return list;
	}
	
	private void list(List<PageMetadata> result, String path, Filter filter, boolean recursive) {
		List<FileMetadata> r = fileManager.list(path, recursive, (e)->{
			return filter.accept(e);
		});
		
		r.stream().forEach((e)->{
			result.add(new PageMetadataImp(e));
		});
		
	}

	private String normalize(String name) {
		return 
			Normalizer.normalize(name, Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.toLowerCase()
			.replaceAll("\\s+", "-");
	}

	@Override
	public void registerTemplate(String id, String name, String formPath, String template) {
		pageTemplateManager.registerTemplate(id, name, formPath, template);
	}

	@Override
	public PageTemplate getTemplate(String id) {
		return pageTemplateManager.getTemplate(id);
	}

	@Override
	public List<PageTemplate> getTemplates() {
		return pageTemplateManager.getTemplates();
	}

	public Map<String,PageTemplate> getTemplatesIdMap(){
		return pageTemplateManager.getTemplatesIdMap();
	}
	
	@Override
	public void unregisterTemplate(String id) {
		pageTemplateManager.unregisterTemplate(id);
	}
	
	public static class PageMetadataImp implements PageMetadata{

		private FileMetadata fileMetadata;
		
		public PageMetadataImp(FileMetadata fileMetadata) {
			this.fileMetadata = fileMetadata;
		}

		@Override
		public String getPath() {
			return fileMetadata.getPath();
		}

		@Override
		public String getId() {
			return fileMetadata.getName();
		}

		@Override
		public Locale getLocale() {
			return (Locale) fileMetadata.getExtMetadata("locale");
		}
		
	}
}
