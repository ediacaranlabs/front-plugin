package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;

import br.com.uoutec.community.ediacaran.plugins.PublicBean;
import br.com.uoutec.community.ediacaran.system.repository.FileManager;
import br.com.uoutec.community.ediacaran.system.repository.FileObjectsTemplateManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.JsonFileManagerHandler;
import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;

public class MenubarObjectsManagerDriver 
	extends FileObjectsTemplateManagerDriver
	implements PublicBean {

	public static final String DRIVER_NAME = "menubar";
	
	public static final String REPOSITORY = "objects/" + DRIVER_NAME + "/";
	
	public MenubarObjectsManagerDriver() {
		super(
				new FileManager(
						new File(System.getProperty("app.base"), REPOSITORY), 
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
