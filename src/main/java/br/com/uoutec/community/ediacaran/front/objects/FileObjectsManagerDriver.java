package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;
import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileValue;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.Filter;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectValue;

public class FileObjectsManagerDriver extends AbstractObjectsManagerDriver {

	private FileManager fileManager;
	
	public FileObjectsManagerDriver(FileManager fileManager, String name) {
		super(name);
		this.fileManager = fileManager;
	}
	
	public List<ObjectMetadata> list(String path, boolean recursive, Filter filter){
		List<FileMetadata> list = fileManager.list(path, recursive, (e)->{
			return filter.accept(toObjectMetadata(e));
		});
		
		List<ObjectMetadata> r = new ArrayList<ObjectMetadata>();
		list.stream().forEach((e->{
			r.add(toObjectMetadata(e));
		}));
		
		return r;
	}
	
	/* get */
	
	protected ObjectValue getAction(ObjectMetadata omd) {

		try {
			ObjectHandler handler = getObjectHandler(omd.getType());
			
			FileMetadata fmd = toFileMetadata(omd);
			FileValue fv = fileManager.get(fmd);
			return fv == null? null : new FileObjectsManagerDriverValue(fv.getFile(), handler.toObject(fv.getObject()));
		}
		catch(Throwable e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/* persist */
	
	protected ObjectValue persistAction(String path, String name, Locale locale, Object obj) throws ObjectsManagerDriverException  {
		ObjectHandler handler = getObjectHandler(obj);
		
		try {
			FileMetadata fmd = toFileMetadata(path, name, locale, handler.getType());
			File file = fileManager.persist(fmd, handler.toData(obj));
			return new FileObjectsManagerDriverValue(file, obj);
		}
		catch(IOException e) {
			throw new ObjectsManagerDriverException(e);
		}
	}
	
	/* delete */
	
	protected void deleteAction(ObjectMetadata omd) throws ObjectsManagerDriverException {
		try {
			FileMetadata fmd = toFileMetadata(omd);
			fileManager.delete(fmd);
		}
		catch(IOException e) {
			throw new ObjectsManagerDriverException(e);
		}
	}
	
	/* private */
	
	private ObjectMetadata toObjectMetadata(FileMetadata fmd) {
		return fmd == null? null : new ObjectMetadata(fmd.getPath(),fmd.getName(), (Locale)fmd.getExtMetadata("locale"), fmd.getType());
	}

	private FileMetadata toFileMetadata(String path, String name, Locale locale, String type) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("locale", locale);
		return new FileMetadata(path, name, type, map);
	}
	
	private FileMetadata toFileMetadata(ObjectMetadata omd) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("locale", omd.getLocale());
		
		return new FileMetadata(omd.getPath(), omd.getId(), omd.getType(), map); 
	}

	public static class FileObjectsManagerDriverValue implements ObjectValue {
		
		private File file;
		
		private long lastModified;
		
		private Object object;

		public FileObjectsManagerDriverValue(File file, Object object) {
			this.file = file;
			this.lastModified = object == null? -1 : file.lastModified();
			this.object = object;
		}
		
		public boolean isValid() {
			return lastModified == file.lastModified();
		}

		public File getFile() {
			return file;
		}

		public long getLastModified() {
			return lastModified;
		}

		public Object getObject() {
			return object;
		}
		
	}	
	
}
