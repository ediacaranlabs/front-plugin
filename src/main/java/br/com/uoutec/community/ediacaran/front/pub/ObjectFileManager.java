package br.com.uoutec.community.ediacaran.front.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.uoutec.community.ediacaran.front.pub.ObjectsManager.Filter;
import br.com.uoutec.community.ediacaran.front.pub.ObjectsManager.ObjectMetadata;

public class ObjectFileManager {

	private static final String PATH_FORMAT = "(/[a-z]+(_[0-9a-z]+))+";
	
	private static final String ID_FORMAT = "[a-z]+(_[0-9a-z]+)+";

	private static final String FULLID_FORMAT = "(" + PATH_FORMAT + ")/(" + ID_FORMAT + ")";
	
	private static final String TYPE_FORMAT = "[0-9a-z]{4,10}";
	
	private static final String LOCALE_FORMAT = "(default)|([a-z]{2,2}_[A-Z]{2,2})";

	private static final String FILENAME_FORMAT = "(" + ID_FORMAT + ")_(" + TYPE_FORMAT + ")_(" + LOCALE_FORMAT + ").obj";
	
	private Pattern fullIdPattern = Pattern.compile(FULLID_FORMAT);
	
	private Pattern idPattern = Pattern.compile(ID_FORMAT);
	
	private Pattern pathPattern = Pattern.compile(PATH_FORMAT);
	
	private Pattern typePattern = Pattern.compile(TYPE_FORMAT);
	
	private Pattern fileNamePattern = Pattern.compile(FILENAME_FORMAT);

	private File basePath;
	
	public ObjectFileManager(File basePath) {
		this.basePath = basePath;
	}
	
	/* list */
	
	public boolean isValidFullId(String fullId) {
		return fullIdPattern.matcher(fullId).matches();
	}

	public boolean isValidType(String type) {
		return typePattern.matcher(type).matches();
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
		
		List<ObjectMetadata> list = new LinkedList<ObjectMetadata>();
		list(list, basePath, filter, recursive);
		
		if(list.size() > 1) {
			throw new IllegalStateException("found: " + list.size());
		}
		
		return list.get(0);
		
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
				ObjectMetadata omd = toObjectMetadata(f);
				if(omd != null && (filter == null || filter.accept(omd))) {
					result.add(omd);
				}
			}
		}
	}
	
	/* load */

	public ObjectValue get(ObjectMetadata omd, ObjectHandler handler) throws IOException {
		
		File file = toFile(omd);
		
		if(!file.exists() || !file.canRead()) {
			return null;
		}

		Object object;
		
		try(FileInputStream stream = new FileInputStream(file)){
			object = handler.getReader().read(new InputStreamReader(stream, StandardCharsets.UTF_8));
		}
		
		return object == null? null : new ObjectValue(file, object);
		
	}
	
	/* persist */
	
	public File persist(String fullId, Locale locale, Object obj, ObjectHandler handler) throws IOException {
		
		File file = toFile(fullId, locale, handler.getType());
		
		if(!file.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + fullId);
		}
		
		try(FileOutputStream stream = new FileOutputStream(file)){
			handler.getWriter().write(obj, new OutputStreamWriter(stream, StandardCharsets.UTF_8));
		}
	
		return file;
	}
	
	/* delete */
	
	public void delete(ObjectMetadata omd) throws IOException {
		
		File file = toFile(omd);
		
		if(!file.getAbsolutePath().startsWith(basePath.getAbsolutePath())){
			throw new IOException("invalid path: " + omd.getPath() + "/" + omd.getId());
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
	
	/* private */
	
	private File toFile(String fullId, Locale locale, String type) {
		
		if(!fullIdPattern.matcher(fullId).matches()) {
			throw new IllegalStateException("invalid id: " + fullId);
		}
		
		if(!typePattern.matcher(type).matches()) {
			throw new IllegalStateException("invalid type: " + type);
		}
		
		fullId = fullId.replaceAll("/+", "/");
		fullId = fullId.replace("/", File.separator);
		
		StringBuilder builder = new StringBuilder(fullId).append("_").append(type).append("_");
		
		if(locale == null) {
			builder.append("default");
		}
		else {
			builder.append(locale.getLanguage()).append("_").append(locale.getCountry());
		}
		
		builder.append(".obj");
		
		return new File(basePath, builder.toString());
	}

	private File toFile(ObjectMetadata omd) {
		
		if(!idPattern.matcher(omd.getId()).matches()) {
			throw new IllegalStateException("invalid id: " + omd.getId());
		}

		if(!pathPattern.matcher(omd.getPath()).matches()) {
			throw new IllegalStateException("invalid path: " + omd.getPath());
		}
		
		if(!typePattern.matcher(omd.getType()).matches()) {
			throw new IllegalStateException("invalid type: " + omd.getType());
		}
		
		String fullId = omd.getPath() + "/" + omd.getId();
		fullId = fullId.replaceAll("/+", "/");
		fullId = fullId.replace("/", File.separator);
		
		StringBuilder builder = new StringBuilder(fullId).append("_").append(omd.getType()).append("_");
		
		if(omd.getLocale() == null) {
			builder.append("default");
		}
		else {
			builder.append(omd.getLocale().getLanguage()).append("_").append(omd.getLocale().getCountry());
		}
		
		builder.append(".obj");
		
		return new File(basePath, builder.toString());
	}
	
	private ObjectMetadata toObjectMetadata(File file) {
		
		String fileName = file.getName();
		
		Matcher m = fileNamePattern.matcher(fileName);
		
		if(!m.matches()) {
			return null;
		}
		
		m.reset();
		
		if(!m.find()) {
			throw new IllegalStateException("id");
		}
		
		String baseName = basePath.getAbsolutePath();
		String pathName = file.getParentFile().getAbsolutePath();
		
		if(!pathName.startsWith(baseName)) {
			throw new IllegalStateException("path");
		}
		
		pathName = pathName.substring(baseName.length());
		pathName = pathName.replace(File.separator, "/");
		
		String id = m.group(1);
		String type = m.group(3);
		
		String localeName = m.group(4);
		String[] localeParts = localeName.split("_");
		Locale locale = localeParts.length == 1? null : new Locale(localeParts[0], localeParts[1]);
		
		return new ObjectMetadata(pathName, id, locale, type);
	}
	
	public static class ObjectValue {
		
		public File file;
		
		public long lastModified;
		
		public Object object;

		public ObjectValue(File file, Object object) {
			this.file = file;
			this.lastModified = object == null? -1 : file.lastModified();
			this.object = object;
		}
		
		public boolean isValid() {
			return lastModified == file.lastModified();
		}
	}
	
	/*
	public static void main(String[] a) {
		System.setProperty("app.base", "C:\\projetos\\ediacaran\\plugins\\front\\front-ediacaran-plugin\\src\\main\\resources\\META-INF\\ediacaran\\objects");
		File base = new File(System.getProperty("app.base"));
		
		ObjectFileManager ofm = new ObjectFileManager();
		ofm.toObjectMetadata(new File(base, "/front/menus/top_menu_bar_22_menu_default.obj"));
		ofm.toObjectMetadata(new File(base, "/front/menus/top_menu_bar_22_menu_pt_BR.obj"));
		ofm.toObjectMetadata(new File(base, "/front/menus/top_menu_bar_menu_pt_BR.obj"));
		ofm.toObjectMetadata(new File(base, "/front/menus/top_menu_menu_pt_BR.obj"));
		ofm.toObjectMetadata(new File(base, "/front/menus/top_menu_pt_BR.obj"));
	}
	*/

}
