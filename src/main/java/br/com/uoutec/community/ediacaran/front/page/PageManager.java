package br.com.uoutec.community.ediacaran.front.page;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.Filter;
import br.com.uoutec.community.ediacaran.front.page.PageTemplateManager.PageTemplate;
import br.com.uoutec.community.ediacaran.front.page.PageTemplateManager.PageTemplateManagerException;

public interface PageManager {

	public static final String PATH_FORMAT = "(\\/[a-z][a-z0-9]+(_[a-z0-9]+)*)*";
	
	public static final String ID_FORMAT = "[a-z][a-z0-9]+(-[a-z0-9]+)*";

	public static final String LOCALE_FORMAT = "[a-z]{2,2}_[A-Z]{2,2}";
	
	void registerPage(String path, String name, Locale locale, Page page) throws PageManagerException;

	void unregisterPage(String path, String name, Locale locale) throws PageManagerException;
	
	Page getPage(String path, Locale locale);
	
	Page getPage(PageMetadata value);
	
	PageMetadata unique();
	
	PageMetadata unique(Filter filter);

	PageMetadata unique(String path, Filter filter);
	
	PageMetadata unique(String path, boolean recursive, Filter filter);
	
	List<PageMetadata> list();
	
	List<PageMetadata> list(Filter filter);

	List<PageMetadata> list(String path, Filter filter);
	
	List<PageMetadata> list(String path, boolean recursive, Filter filter);
	
	void registerTemplate(String id, String name, String formPath, String template) throws PageTemplateManagerException;

	void unregisterTemplate(String id);
	
	PageTemplate getTemplate(String id);
	
	List<PageTemplate> getTemplates();
	
	Map<String,PageTemplate> getTemplatesIdMap();
	
	public class PageManagerException extends Exception{

		private static final long serialVersionUID = -5856344904897577103L;

		public PageManagerException() {
			super();
		}

		public PageManagerException(String message, Throwable cause) {
			super(message, cause);
		}

		public PageManagerException(String message) {
			super(message);
		}

		public PageManagerException(Throwable cause) {
			super(cause);
		}
		
	}
	
	public interface PageMetadata {
		
		String getPath();
		
		String getId();
		
		Locale getLocale();
		
	}
	
	public static class PageMetadataImp implements PageMetadata{
		
		private final String path;
		
		private final String id;
		
		private final Locale locale;

		public PageMetadataImp(String path, String id, Locale locale) {
			this.path = path;
			this.id = id;
			this.locale = locale;
		}

		public String getPath() {
			return path;
		}

		public String getId() {
			return id;
		}

		public Locale getLocale() {
			return locale;
		}
		
	}
}
