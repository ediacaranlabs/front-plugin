package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;

import br.com.uoutec.community.ediacaran.front.page.PageFileManagerHandler;

public class PagesObjectsManagerDriver extends FileObjectsManagerDriver{

	public static final String DRIVER_NAME = "pages";
	
	public static final String DRIVER_PATH = "/" + DRIVER_NAME;
	
	public PagesObjectsManagerDriver() {
		super(
				new FileManager(
						new File(System.getProperty("app.web")), 
						new PageFileManagerHandler()
				), DRIVER_NAME
		);
	}
	
}
