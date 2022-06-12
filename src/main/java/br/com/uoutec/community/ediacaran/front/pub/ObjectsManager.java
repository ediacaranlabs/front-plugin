package br.com.uoutec.community.ediacaran.front.pub;

import java.util.Locale;

public interface ObjectsManager {

	void registerObject(String id, Locale locale, Object object);
	
	void unregisterObject(String id, String type, Locale locale);
	
	Object getObject(String id, String type);
	
	Object getObject(String id, String type, Locale locale);
	
	void registerObjectHandler(ObjectHandler encoder);
	
	void unregisterObjectHandler(ObjectHandler encoder);
	
}
