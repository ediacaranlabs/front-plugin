package br.com.uoutec.community.ediacaran.front.objects;

import java.util.List;
import java.util.Locale;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.Filter;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectValue;

public interface ObjectsManagerDriver {

	void registerObjectHandler(ObjectHandler handler);

	void unregisterObjectHandler(ObjectHandler handler);
	
	ObjectHandler getDefaultObjectHandler();

	void setDefaultObjectHandler(ObjectHandler defaultObjectHandler);
	
	boolean isCacheable();
	
	String getName();

	ObjectMetadata unique(String path, String name, Locale locale, boolean recursive, Filter type);

	List<ObjectMetadata> list(String path, String name, Locale locale, boolean recursive, Filter type);
	
	ObjectValue get(ObjectMetadata omd);
	
	ObjectValue persist(String path, String name, Locale locale, Object object) throws ObjectsManagerDriverException;
	
	void delete(ObjectMetadata omd) throws ObjectsManagerDriverException;
	
	void addListener(ObjectsManagerDriverListener listener);

	void removeListener(ObjectsManagerDriverListener listener);
	
	public static interface ObjectsManagerDriverListener{
		
		default void beforeLoad(ObjectMetadata omd) {};
		
		default void afterLoad(ObjectMetadata omd, ObjectValue obj) {};
		
		default void beforePersist(String path, String name, Locale locale, Object obj) {};
		
		default void afterPersist(String path, String name, Locale locale, ObjectValue objValue) {};

		default void beforeDelete(ObjectMetadata omd) {};
		
		default void afterDelete(ObjectMetadata omd) {};
		
	}
	
}
