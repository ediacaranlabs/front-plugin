package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FileManager {

	private static final String PATH_FORMAT = "(\\/[a-z][a-z0-9]+([_-][a-z0-9]+)*)+";
	
	private Pattern pathPattern = Pattern.compile(PATH_FORMAT);

	private FileManagerHandler fileManagerHandler;
	
	private File basePath;
	
	public FileManager(File basePath, FileManagerHandler fileManagerHandler) {
		this.basePath = basePath;
		this.fileManagerHandler = fileManagerHandler;
	}
	
	public FileMetadata toMetadata(File base, String path) {
		return fileManagerHandler.toMetadata(base, path);
	}

	public FileMetadata toMetadata(File base, File file) {
		return fileManagerHandler.toMetadata(base, file);
	}
	
	public FileMetadata unique(){
		return unique(null, null);
	}
	
	public FileMetadata unique(Filter filter){
		return unique(null, true, filter);
	}

	public FileMetadata unique(String path, Filter filter){
		return unique(path, true, filter);
	}
	
	public FileMetadata unique(String path, boolean recursive, Filter filter){
		
		List<FileMetadata> list = list(path, recursive, filter);
		
		if(list.size() > 1) {
			throw new IllegalStateException("found: " + list.size());
		}
		
		return list.isEmpty()? null : list.get(0);
		
	}
	
	public List<FileMetadata> list(){
		return list(null);
	}
	
	public List<FileMetadata> list(Filter filter){
		return list(null, true, filter);
	}

	public List<FileMetadata> list(String path, Filter filter){
		return list(path, true, filter);
	}
	
	public List<FileMetadata> list(String path, boolean recursive, Filter filter){
		
		File base;
		
		if(path != null && !path.isEmpty()) {
			if(!pathPattern.matcher(path).matches()) {
				throw new IllegalStateException("invalid path: " + path);
			}
			
			path = fileManagerHandler.toFilePath(path);
			base = new File(basePath, path);
		}
		else {
			base = basePath;
		}
		
		List<FileMetadata> list = new LinkedList<FileMetadata>();
		list(list, base, filter, recursive);
		return list;
	}
	
	private void list(List<FileMetadata> result, File path, Filter filter, boolean recursive) {
		
		File[] l = path.listFiles();
		
		for(File f: l) {
			if(f.isDirectory() && recursive) {
				list(result, f, filter, recursive);
			}
			else
			if(f.isFile()) {
				FileMetadata omd = fileManagerHandler.toMetadata(this.basePath, f);
				if(omd != null && (filter == null || filter.accept(omd))) {
					result.add(omd);
				}
			}
		}
	}
	
	/* load */

	public FileValue get(FileMetadata fmd) throws IOException {
		
		File file = fileManagerHandler.toFile(basePath, fmd);

		if(!file.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + fmd);
		}
		
		if(!file.exists() || !file.canRead()) {
			return null;
		}

		Object object = fileManagerHandler.read(file, fmd);
		
		return object == null? null : new FileValue(file, object);
		
	}
	
	/* persist */
	
	public File persist(FileMetadata fmd, Object object) throws IOException {
		
		File file = fileManagerHandler.toFile(basePath, fmd);
		
		if(!file.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + fmd);
		}
		
		file.getParentFile().mkdirs();
		fileManagerHandler.write(file, fmd, object);
		return file;
	}
	
	/* delete */
	
	public void delete(FileMetadata fmd) throws IOException {
		
		File file = fileManagerHandler.toFile(basePath, fmd);
		
		if(!file.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + fmd);
		}
		
		delete(basePath, file);
	}
	
	private void delete(File base, File file) throws IOException {
		
		file.delete();
		
		File parent = file.getParentFile();
		
		if(parent.equals(base)) {
			return;
		}
		
		if(parent.listFiles(e->e.isFile()).length == 0) {
			delete(base, parent);
		}
		
	}
	
	@FunctionalInterface
	public static interface Filter {
		
		boolean accept(FileMetadata omd);
		
	}
	
	public static class FileValue {
		
		private File file;
		
		private long lastModified;
		
		private Object object;

		public FileValue(File file, Object object) {
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
	
	public static class FileMetadata {
		
		private String path;
		
		private String name;
		
		private String type;

		private Map<String,Object> extMetadata;

		public FileMetadata(FileMetadata value) {
			this(value.path, value.name, value.type, value.extMetadata);
		}
		
		public FileMetadata(String path, String name, String type, Map<String, Object> extMetadata) {
			this.path = path;
			this.name = name;
			this.type = type;
			this.extMetadata = extMetadata;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		public Object getExtMetadata(String name) {
			return extMetadata.get(name);
		}
		
	}

}
