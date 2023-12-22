package br.com.uoutec.community.ediacaran.front.objects;

import br.com.uoutec.application.SystemProperties;
import br.com.uoutec.application.io.Vfs;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageFileManagerHandler;
import br.com.uoutec.community.ediacaran.system.repository.FileManager;
import br.com.uoutec.community.ediacaran.system.repository.FileObjectsTemplateManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.ObjectHandlerImp;
import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;

public class PagesObjectsManagerDriver 
	extends FileObjectsTemplateManagerDriver {

	public static final String DRIVER_NAME = "pages";
	
	public static final String DRIVER_PATH = "/" + DRIVER_NAME;
	
	public PagesObjectsManagerDriver() {
		super(
				new FileManager(
						Vfs.getPath(SystemProperties.getProperty("app.web")), 
						new PageFileManagerHandler()
				), DRIVER_NAME
		);
		setDefaultObjectHandler(new ObjectHandlerImp());
	}

	@Override
	public ObjectTemplate getTemplate(Object object) {
		Page page = (Page)object;
		return super.getTemplateByName(page.getTemplate());
	}

}
