package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;

import br.com.uoutec.community.ediacaran.front.page.ObjectTemplate;

public class MenubarObjectsManagerDriver 
	extends FileObjectsTemplateManagerDriver {

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
