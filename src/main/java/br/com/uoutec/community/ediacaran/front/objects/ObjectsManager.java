package br.com.uoutec.community.ediacaran.front.objects;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface ObjectsManager {

	void registerObject(String id, Locale locale, Object object);
	
	void unregisterObject(String id, Locale locale);
	
	ObjectEntry getObjects(String id);
	
	Object getObject(String id);
	
	Object getObject(String id, Locale locale);
	
	List<ObjectMetadata> listMetadata(String path, String name, Locale locale, boolean recursive);
	
	List<Object> list(String path, String name, Locale locale, boolean recursive);
	
	List<ObjectEntry> listObjects(String path, String name, boolean recursive);
	
	void registerObjectHandler(ObjectHandler encoder);
	
	void unregisterObjectHandler(ObjectHandler encoder);
	
	void addListener(ObjectListener listener);

	void removeListener(ObjectListener listener);
	
	void flush();
	
	public static interface ObjectListener{
		
		void beforeLoad(String id, Locale locale);
		
		void afterLoad(String id, Locale locale, Object obj);
		
		void beforeRegister(String id, Locale locale, Object object);
		
		void afterRegister(String id, Locale locale, Object object);

		void beforeUnregister(String id, Locale locale);
		
		void afterUnregister(String id, Locale locale);
		
	}
	
	public static interface Filter {
		
		boolean accept(ObjectMetadata omd);
		
	}
	
	public static class ObjectEntry{
		
		private String path;
		
		private String id;
		
		private Map<Locale,Object> locales;

		public ObjectEntry(String path, String id, Map<Locale, Object> locales) {
			this.path = path;
			this.id = id;
			this.locales = locales;
		}

		public String getPath() {
			return path;
		}

		public String getId() {
			return id;
		}

		public String getFullId() {
			return path + "/" + id;
		}
		
		public Object getObject() {
			return locales.get(null);
		}
		
		public Object getObject(Locale locale) {
			return locales.get(locale);
		}
		
		public Map<Locale, Object> getLocales() {
			return locales;
		}
	
		
	}
	
	public static class ObjectMetadata{
		
		private String path;
		
		private String id;
		
		private Locale locale;
		
		private String type;

		public ObjectMetadata(String path, String id, Locale locale, String type) {
			this.path = path;
			this.id = id;
			this.locale = locale;
			this.type = type;
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

		public String getType() {
			return type;
		}
		
	}
	
}
