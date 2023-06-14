package br.com.uoutec.community.ediacaran.front.objects;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerDriver.ObjectsManagerDriverListener;
import br.com.uoutec.community.ediacaran.plugins.PublicBean;

public interface ObjectsManager extends PublicBean {

	ObjectMetadata registerObject(String id, Locale locale, Object object);
	
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

	void addListener(String driverName, ObjectsManagerDriverListener listener);

	void removeListener(String driverName, ObjectsManagerDriverListener listener);
	
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
						if(!omd.getPathMetadata().getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPathMetadata().getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || omd.getPathMetadata().getId().matches(name);
			}
			
		},
		
		EQUAL(){
			
			@Override
			public boolean accept(String path, String name, Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPathMetadata().getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPathMetadata().getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || omd.getPathMetadata().getId().equals(name);
			}
			
		},

		EQUAL_LOCALE(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPathMetadata().getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPathMetadata().getPath().equals(path)) {
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
				
				return name == null || omd.getPathMetadata().getId().equals(name);
			}
			
		},
		
		CONTAINS(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPathMetadata().getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPathMetadata().getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || omd.getPathMetadata().getId().contains(name);
			}
			
		},
		
		NOT_EQUAL(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPathMetadata().getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPathMetadata().getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || !omd.getPathMetadata().getId().equals(name);
			}
			
		},
		
		NOT_CONTAINS(){
			
			@Override
			public boolean accept(String path, String name,Locale locale, boolean recursive, ObjectMetadata omd) {
				
				if(path != null) {
					if(recursive) {
						if(!omd.getPathMetadata().getPath().startsWith(path)) {
							return false;
						}
					}
					else
					if(!omd.getPathMetadata().getPath().equals(path)) {
						return false;
					}
				}
				
				if(locale != null) {
					if(!omd.getLocale().equals(locale)) {
						return false;
					}
				}


				return name == null || !omd.getPathMetadata().getId().contains(name);
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
		
		private PathMetadata pathMetadata;
		
		private Locale locale;

		public ObjectMetadata(PathMetadata pathMetadata, Locale locale) {
			this.pathMetadata = pathMetadata;
			this.locale = locale;
		}

		public PathMetadata getPathMetadata() {
			return pathMetadata;
		}

		public Locale getLocale() {
			return locale;
		}

		
	}
	
	public static interface ObjectValue {
		
		boolean isValid();

		Object getObject();
		
	}
	
}
