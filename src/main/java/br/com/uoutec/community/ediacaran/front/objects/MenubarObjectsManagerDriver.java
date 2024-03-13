package br.com.uoutec.community.ediacaran.front.objects;

import br.com.uoutec.application.SystemProperties;
import br.com.uoutec.application.io.Vfs;
import br.com.uoutec.community.ediacaran.system.repository.FileManager;
import br.com.uoutec.community.ediacaran.system.repository.FileObjectsTemplateManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.JsonFileManagerHandler;
import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;
import br.com.uoutec.ediacaran.core.plugins.PublicBean;

public class MenubarObjectsManagerDriver 
	extends FileObjectsTemplateManagerDriver
	implements PublicBean {

	public static final String DRIVER_NAME = "menubar";
	
	public static final String REPOSITORY = 
			SystemProperties.getProperty("app.base") + "/objects/" + DRIVER_NAME + "/";
	
	public MenubarObjectsManagerDriver() {
		super(
				new FileManager(
						Vfs.getPath(REPOSITORY), 
						new JsonFileManagerHandler()
				), DRIVER_NAME
		);
		setDefaultObjectHandler(new MenubarObjectHandler());
	}

	@Override
	public ObjectTemplate getTemplate(Object object) {
		throw new UnsupportedOperationException();
	}

}
