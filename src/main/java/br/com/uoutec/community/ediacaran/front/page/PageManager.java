package br.com.uoutec.community.ediacaran.front.page;

import java.util.List;
import java.util.Locale;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.Filter;

public interface PageManager {

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
		
		String getID();
		
		Locale getLocale();
		
	}
	
}
