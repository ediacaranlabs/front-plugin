package br.com.uoutec.community.ediacaran.front.objects;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface ObjectsManager {

	void registerObject(String id, Locale locale, Object object);
	
	ObjectMetadata registerObjectIfNotExist(String id, Locale locale, Object object);
	
	void unregisterObject(String id, Locale locale);
	
	ObjectEntry getObjects(String id);
	
	Object getObject(String id);
	
	Object getObject(String id, Locale locale);
	
	Object getObject(ObjectMetadata omd);
	
	List<ObjectMetadata> listMetadata(String path, String name, Locale locale, boolean recursive, Filter filter);
	
	List<Object> list(String path, String name, Locale locale, boolean recursive, Filter filter);
	
	List<ObjectEntry> listObjects(String path, String name, boolean recursive, Filter filter);
	
	void registerDriver(ObjectsManagerDriver driver) throws ObjectsManagerDriverException;
	
	void unregisterDriver(ObjectsManagerDriver driver);
	
	ObjectsManagerDriver getDriver(String name);
	
	void addListener(ObjectListener listener);

	void removeListener(ObjectListener listener);
	
	void flush();
	
	public static interface ObjectListener{
		
		default void beforeLoad(String id, Locale locale) {};
		
		default void afterLoad(String id, Locale locale, Object obj) {};
		
		default void beforeRegister(String id, Locale locale, Object object) {};
		
		default void afterRegister(String id, Locale locale, Object object) {};

		default void beforeUnregister(String id, Locale locale) {};
		
		default void afterUnregister(String id, Locale locale) {};
		
	}
	
	public static interface Filter {
		
		boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd);
		
	}
	
	public static enum SearchType implements Filter{
		
		REGEX(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || omd.getId().matches(name);
			}
			
		},
		
		EQUAL(){
			
			@Override
			public boolean accept(String path, String name, Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || omd.getId().equals(name);
			}
			
		},

		EQUAL_LOCALE(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale == null){
					if(omd.getLocale() != null) {
						return false;
					}
				}
				else
				if(!locale.equals(omd.getLocale())){
					return false;
				}
				
				return name == null || omd.getId().equals(name);
			}
			
		},
		
		CONTAINS(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || omd.getId().contains(name);
			}
			
		},
		
		NOT_EQUAL(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || !omd.getId().equals(name);
			}
			
		},
		
		NOT_CONTAINS(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || !omd.getId().contains(name);
			}
			
		};

		@Override
		public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
			return false;
		}
		
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
	
	public static interface ObjectValue {
		
		boolean isValid();

		Object getObject();
		
	}	
}
