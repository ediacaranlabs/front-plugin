package br.com.uoutec.community.ediacaran.front.objects;

import java.util.List;
import java.util.Locale;

import br.com.uoutec.application.SystemProperties;
import br.com.uoutec.application.io.Vfs;
import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.community.ediacaran.system.repository.FileManager;
import br.com.uoutec.community.ediacaran.system.repository.FileObjectsTemplateManagerDriver;
import br.com.uoutec.community.ediacaran.system.repository.Filter;
import br.com.uoutec.community.ediacaran.system.repository.JsonFileManagerHandler;
import br.com.uoutec.community.ediacaran.system.repository.ObjectMetadata;
import br.com.uoutec.community.ediacaran.system.repository.ObjectTemplate;
import br.com.uoutec.community.ediacaran.system.repository.ObjectValue;
import br.com.uoutec.community.ediacaran.system.repository.ObjectsManagerDriverException;
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

	public boolean isCacheable(ObjectMetadata omd) {
		return false;
	}
	
	@Override
	public ObjectTemplate getTemplate(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected List<ObjectMetadata> listAction(String path, String name, Locale locale, boolean recursive,
			Filter filter) {
		return ContextSystemSecurityCheck
				.doPrivileged(()->super.listAction(path, name, locale, recursive, filter));
	}

	@Override
	protected ObjectValue getAction(ObjectMetadata omd) {
		return ContextSystemSecurityCheck
				.doPrivileged(()->super.getAction(omd));
	}

	@Override
	protected ObjectValue persistAction(String path, String name, Locale locale, Object obj)
			throws ObjectsManagerDriverException {
		return ContextSystemSecurityCheck
				.doPrivileged(()->super.persistAction(path, name, locale, obj));
	}

	@Override
	protected void deleteAction(ObjectMetadata omd) throws ObjectsManagerDriverException {
		ContextSystemSecurityCheck
				.doPrivileged(()->{
					super.deleteAction(omd);
					return null;
				});
	}

}
