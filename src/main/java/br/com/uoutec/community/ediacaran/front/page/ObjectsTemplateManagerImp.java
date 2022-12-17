package br.com.uoutec.community.ediacaran.front.page;

import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerDriverException;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerImp;

@Singleton
public class ObjectsTemplateManagerImp
	extends ObjectsManagerImp	
	implements ObjectsTemplateManager {

	@Override
	public ObjectTemplate getTemplate(String driverName, Object object) throws ObjectsManagerDriverException {
		
		ObjectsTemplateManagerDriver driver = (ObjectsTemplateManagerDriver)getSecureDriver(driverName);
		
		return driver.getTemplate(object);
	}

	@Override
	public List<ObjectTemplate> getTemplates(String driverName) {
		ObjectsTemplateManagerDriver driver = (ObjectsTemplateManagerDriver)getSecureDriver(driverName);
		
		return driver.getTemplates();
	}

	@Override
	public Map<String, ObjectTemplate> getTemplatesIdMap(String driverName) {
		ObjectsTemplateManagerDriver driver = (ObjectsTemplateManagerDriver)getSecureDriver(driverName);
		
		return driver.getTemplatesIdMap();
	}

	@Override
	public ObjectTemplate getTemplateById(String driverName, String name) {
		ObjectsTemplateManagerDriver driver = (ObjectsTemplateManagerDriver)getSecureDriver(driverName);
		return driver.getTemplateByName(name);
	}

}
