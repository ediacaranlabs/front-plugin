package br.com.uoutec.community.ediacaran.front.pub;

import java.util.List;
import java.util.Locale;

public interface ObjectsManager {

	void registerObject(String id, Locale locale, Object object);
	
	void unregisterObject(String id, Locale locale);
	
	List<Object> getObjects(String id);
	
	Object getObject(String id);
	
	Object getObject(String id, Locale locale);
	
	void registerObjectHandler(ObjectHandler encoder);
	
	void unregisterObjectHandler(ObjectHandler encoder);
	
	void addListener(ObjectListener listener);

	void removeListener(ObjectListener listener);
	
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
