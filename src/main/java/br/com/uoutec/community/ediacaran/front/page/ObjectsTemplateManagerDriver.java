package br.com.uoutec.community.ediacaran.front.page;

import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerDriver;

public interface ObjectsTemplateManagerDriver extends ObjectsManagerDriver{

	void registerTemplate(ObjectTemplate template);
	
	void unregisterTemplate(String id);
	
	ObjectTemplate getTemplateByName(String id);
	
	ObjectTemplate getTemplate(Object object);
	
	List<ObjectTemplate> getTemplates();

	Map<String,ObjectTemplate> getTemplatesIdMap();
	
}
