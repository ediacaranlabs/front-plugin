package br.com.uoutec.community.ediacaran.front.page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.uoutec.application.io.Path;
import br.com.uoutec.application.io.Vfs;
import br.com.uoutec.community.ediacaran.system.repository.FileManager;
import br.com.uoutec.community.ediacaran.system.repository.FileManagerHandler;
import br.com.uoutec.community.ediacaran.system.repository.FileMetadata;
import br.com.uoutec.community.ediacaran.system.util.DataUtil.ClassTypeAdapter;

public class PageFileManagerHandler implements FileManagerHandler{

	public static final String PATH_FORMAT = "(\\/[a-z0-9][a-z0-9]+([_-][a-z0-9]+)*)*";
	
	public static final String ID_FORMAT = "[a-z0-9][a-z0-9]+([_-][a-z0-9]+)*";

	public static final String LOCALE_FORMAT = "[a-z]{2,2}_[A-Z]{2,2}";
	
	private static final Gson gson;

	private static final String FILENAME_FORMAT = "(" + ID_FORMAT + ")(_" + LOCALE_FORMAT + ")?\\.pag";

	static{
		gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.TRANSIENT | Modifier.STATIC)
        .registerTypeAdapter(Class.class, new ClassTypeAdapter())
        .create();		
	}
	
	private Pattern idPattern = Pattern.compile(ID_FORMAT);
	
	private Pattern pathPattern = Pattern.compile(PATH_FORMAT);
	
	private Pattern fileNamePattern = Pattern.compile(FILENAME_FORMAT);
	
	private DataPageObjectParser dataPageObjectParser;
	
	private PageObjectDataParser pageObjectDataParser;
	
	public PageFileManagerHandler() {
		this.dataPageObjectParser = new DataPageObjectParser();
		this.pageObjectDataParser = new PageObjectDataParser();
	}
	
	@Override
	public FileMetadata toMetadata(Path base, Path file) {
		
		String fileName = file.getName();
		
		Matcher m = fileNamePattern.matcher(fileName);
		
		if(!m.matches()) {
			return null;
		}
		
		m.reset();
		
		if(!m.find()) {
			throw new IllegalStateException("id");
		}
		
		String baseName = base.getFullName();
		String pathName = file.getParent().getFullName();
		
		if(!pathName.startsWith(baseName)) {
			throw new IllegalStateException("path");
		}
		
		pathName = pathName.substring(baseName.length());
		pathName = pathName.replace(File.separator, "/");
		
		String id = m.group(1);
		String localeName = m.group(3);
		
		String[] localeParts = localeName == null? null : localeName.substring(1).split("_");
		Locale locale = localeParts == null? null : new Locale(localeParts[0], localeParts[1]);
		
		Map<String,Object> md = new HashMap<String,Object>();
		md.put("locale", locale);
		return new FileMetadata(pathName.isEmpty()? FileManager.BASE_PATH : pathName, id, "pag", md);
	}
	
	@Override
	public Path toFile(Path base, FileMetadata omd) {
		
		if(!idPattern.matcher(omd.getName()).matches()) {
			throw new IllegalStateException("invalid id: " + omd.getName());
		}

		if(!FileManager.BASE_PATH.equals(omd.getPath()) && !pathPattern.matcher(omd.getPath()).matches()) {
			throw new IllegalStateException("invalid path: " + omd.getPath());
		}
		
		String path = toFilePath(omd.getPath() + "/" + omd.getName());
		
		StringBuilder builder = new StringBuilder(path);
		
		Locale locale = (Locale) omd.getExtMetadata("locale");
		if(locale != null) {
			builder.append("_").append(locale.getLanguage()).append("_").append(locale.getCountry());
		}
		
		builder.append(".pag");
		
		return base.getPath(builder.toString());
	}
	
	private Path toContentFile(Path fileMtadata, FileMetadata omd) {
		
		String name = omd.getName();
		Locale locale = (Locale) omd.getExtMetadata("locale");
		
		StringBuilder builder = new StringBuilder(name);
		
		if(locale != null) {
			builder.append("_").append(locale.getLanguage()).append("_").append(locale.getCountry());
		}
		
		builder.append(".cpag");
		
		return fileMtadata.getParent().getPath(builder.toString());
	}

	private Path toThumbnailFile(Path fileMtadata, FileMetadata omd) {
		
		String name = omd.getName();
		Locale locale = (Locale) omd.getExtMetadata("locale");
		
		StringBuilder builder = new StringBuilder(name);
		
		if(locale != null) {
			builder.append("_").append(locale.getLanguage()).append("_").append(locale.getCountry());
		}
		
		builder.append(".png");
		
		return fileMtadata.getParent().getPath(builder.toString());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object read(Path file, FileMetadata metadata) throws IOException {

		Path contentFile   = toContentFile(file, metadata);
		Path thumbnailFile = toThumbnailFile(file, metadata);
		
		Object data;
		
		try(Reader reader = getReader(file)){
			data = gson.fromJson(reader, Object.class);
		}
		
		if(data == null) {
			return data;
		}
		
		Page page = dataPageObjectParser.toObject((Map<String, Object>) data);

		if(contentFile.exists()) {
			page.setContent(getReader(contentFile));
		}
		
		if(thumbnailFile.exists()) {
			page.setThumbnail(thumbnailFile);
		}
		
		return page;
	}

	private Reader getReader(Path file) throws FileNotFoundException {
		return new InputStreamReader(file.openInputStream(), StandardCharsets.UTF_8);
	}
	
	@Override
	public void write(Path file, FileMetadata metadata, Object value) throws FileNotFoundException, IOException {
		
		Path contentFile   = toContentFile(file, metadata);
		Path thumbnailFile = toThumbnailFile(file, metadata);
		
		Page page = (Page)value;
		
		Object data = pageObjectDataParser.toData(page);
		
		try(Writer stream = getWriter(file)){
			gson.toJson(data, Object.class, stream);
			stream.flush();
		}
		
		try(Writer writer = getWriter(contentFile)){
		
			Object content = page.getContent();
			
			if(content instanceof String) {
				writer.append((String)content);
			}
			else
			if(content instanceof Reader) {
				
				Reader reader = (Reader)content;
				char[] buf = new char[4096];
				int l;
				
				try{
					while((l = reader.read(buf, 0, buf.length)) > 0 ) {
						writer.write(buf, 0, l);
					}
				}
				finally {
					reader.close();
				}
				
			}
			else {
				writer.append("");
			}
				
			
			writer.flush();
		}
		
		if(page.getThumbnail() != null && !page.getThumbnail().equals(thumbnailFile)) {
			Path copied = thumbnailFile;
		    Path originalPath = page.getThumbnail();
		    Vfs.copy(originalPath, copied);
		    page.setThumbnail(thumbnailFile);
		}
	}

	public Writer getWriter(Path file) throws FileNotFoundException {
		return new OutputStreamWriter(file.openOutputStream(), StandardCharsets.UTF_8);
	}
	
	@Override
	public void delete(Path file, FileMetadata metadata) throws IOException {
		
		Path contentFile   = toContentFile(file, metadata);
		Path thumbnailFile = toThumbnailFile(file, metadata);
		
		contentFile.delete();
		thumbnailFile.delete();
		file.delete();
		
	}
	
}
