package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;
import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileValue;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.Filter;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectValue;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerImp.ObjectFileMetadataManager;

public class ObjectFileManager {

	private static final String PATH_FORMAT = "(\\/[a-z][a-z0-9]+(_[a-z0-9]+)*)+";
	
	private Pattern pathPattern = Pattern.compile(PATH_FORMAT);

	private File basePath;
	
	private FileManager fileManager;
	
	public ObjectFileManager(File basePath) {
		
		this.fileManager = new FileManager(basePath, new AbstractFileManagerHandler() {
			
			@Override
			public Object read(File file, FileMetadata metadata) throws IOException {

				ObjectFileMetadataManager omd = (ObjectFileMetadataManager)metadata;
				Object object;
				
				try(InputStream stream = new FileInputStream(file)){
					object = omd.getHandler().getReader().read(stream);
				}
				
				return object;
				
			}

			@Override
			public void write(File file, FileMetadata metadata, Object value) throws FileNotFoundException, IOException {
				
				ObjectFileMetadataManager omd = (ObjectFileMetadataManager)metadata;
				
				ObjectHandler handler = omd.getHandler();
				try(OutputStream stream = new FileOutputStream(file)){
					handler.getWriter().write(value, stream);
					stream.flush();
				}
				
			}
			
		});
		this.basePath = basePath;
	}
	
	public ObjectMetadata unique(){
		return unique(null, null);
	}
	
	public ObjectMetadata unique(Filter filter){
		return unique(null, true, filter);
	}

	public ObjectMetadata unique(String path, Filter filter){
		return unique(path, true, filter);
	}
	
	public ObjectMetadata unique(String path, boolean recursive, Filter filter){
		
		List<ObjectMetadata> list = list(path, recursive, filter);
		
		if(list.size() > 1) {
			throw new IllegalStateException("found: " + list.size());
		}
		
		return list.isEmpty()? null : list.get(0);
		
	}
	
	public List<ObjectMetadata> list(){
		return list(null);
	}
	
	public List<ObjectMetadata> list(Filter filter){
		return list(null, true, filter);
	}

	public List<ObjectMetadata> list(String path, Filter filter){
		return list(path, true, filter);
	}
	
	public List<ObjectMetadata> list(String path, boolean recursive, Filter filter){
		
		File base;
		
		if(path != null) {
			if(!pathPattern.matcher(path).matches()) {
				throw new IllegalStateException("invalid path: " + path);
			}
			
			path = path.replace("/", File.separator);
			base = new File(basePath, path);
		}
		else {
			base = basePath;
		}
		
		List<ObjectMetadata> list = new LinkedList<ObjectMetadata>();
		list(list, base, filter, recursive);
		return list;
	}
	
	private void list(List<ObjectMetadata> result, File path, Filter filter, boolean recursive) {
		
		File[] l = path.listFiles();
		
		for(File f: l) {
			if(f.isDirectory() && recursive) {
				list(result, f, filter, recursive);
			}
			else
			if(f.isFile()) {
				ObjectMetadata omd = toObjectMetadata(fileManager.toMetadata(basePath, f));
				if(omd != null && (filter == null || filter.accept(omd))) {
					result.add(omd);
				}
			}
		}
	}
	
	/* get */
	
	public ObjectValue get(ObjectMetadata omd, ObjectHandler handler) throws IOException {
		FileMetadata fmd = toFileMetadata(omd, handler);
		FileValue fv = fileManager.get(fmd);
		//return fv == null? null : new ObjectValue(fv.getFile(), fv.getObject());
		return null;
	}
	
	/* persist */
	
	public File persist(String path, String name, Locale locale, Object obj, ObjectHandler handler) throws IOException {
		FileMetadata fmd = toFileMetadata(path, name, locale, obj, handler);
		File file = fileManager.persist(fmd, obj);
		return file;
	}
	
	/* delete */
	
	public void delete(ObjectMetadata omd) throws IOException {
		FileMetadata fmd = toFileMetadata(omd, null);
		fileManager.delete(fmd);
	}
	
	/* private */
	
	private ObjectMetadata toObjectMetadata(FileMetadata fmd) {
		return fmd == null? null : new ObjectMetadata(fmd.getPath(),fmd.getName(), (Locale)fmd.getExtMetadata("locale"), fmd.getType());
	}

	private FileMetadata toFileMetadata(String path, String name, Locale locale, Object obj, ObjectHandler handler) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("locale", locale);
		return new ObjectFileMetadataManager(new FileMetadata(path, name, handler.getType(), map), handler);
	}
	
	private FileMetadata toFileMetadata(ObjectMetadata omd, ObjectHandler handler) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("locale", omd.getLocale());
		
		return new ObjectFileMetadataManager(new FileMetadata(omd.getPath(), omd.getId(), omd.getType(), map), handler); 
	}

}
