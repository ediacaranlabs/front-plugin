package br.com.uoutec.community.ediacaran.front.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import br.com.uoutec.community.ediacaran.core.system.util.DataUtil.ClassTypeAdapter;
import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;
import br.com.uoutec.community.ediacaran.front.objects.FileManagerHandler;

public class PageFileManagerHandler implements FileManagerHandler{

	private static final Gson gson;
	
	private static final String PATH_FORMAT = "(\\/[a-z][a-z0-9]+(_[a-z0-9]+)*)+";
	
	private static final String ID_FORMAT = "[a-z][a-z0-9]+(-[a-z0-9]+)*";

	private static final String LOCALE_FORMAT = "(default)|([a-z]{2,2}_[A-Z]{2,2})";

	private static final String FILENAME_FORMAT = "(" + ID_FORMAT + ")_(" + LOCALE_FORMAT + ").pag";

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
	public FileMetadata toMetadata(File base, File file) {
		
		String fileName = file.getName();
		
		Matcher m = fileNamePattern.matcher(fileName);
		
		if(!m.matches()) {
			return null;
		}
		
		m.reset();
		
		if(!m.find()) {
			throw new IllegalStateException("id");
		}
		
		String baseName = base.getAbsolutePath();
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
		
		Map<String,Object> md = new HashMap<String,Object>();
		md.put("locale", locale);
		return new FileMetadata(pathName, id, type, md);
	}
	
	@Override
	public File toFile(File base, FileMetadata omd) {
		
		if(!idPattern.matcher(omd.getName()).matches()) {
			throw new IllegalStateException("invalid id: " + omd.getName());
		}

		if(!pathPattern.matcher(omd.getPath()).matches()) {
			throw new IllegalStateException("invalid path: " + omd.getPath());
		}
		
		String path = toFilePath(omd.getPath() + "/" + omd.getName());
		Locale locale = (Locale) omd.getExtMetadata("locale");
		
		StringBuilder builder = new StringBuilder(path).append("_").append("_");
		
		if(locale == null) {
			builder.append("default");
		}
		else {
			builder.append(locale.getLanguage()).append("_").append(locale.getCountry());
		}
		
		builder.append(".pag");
		
		return new File(base, builder.toString());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object read(File file, FileMetadata metadata) throws IOException {

		Object data;
		
		try(Reader reader = getReader(file)){
			data = gson.fromJson(reader, Object.class);
		}
		
		if(data == null) {
			return data;
		}
		
		Page page = dataPageObjectParser.toObject((Map<String, Object>) data);

		Locale locale = (Locale) metadata.getExtMetadata("locale");
		
		StringBuilder b = new StringBuilder(metadata.getName());
		
		if(locale != null) {
			b.append("_").append(locale.getLanguage()).append("_").append(locale.getCountry());
		}
		b.append(".cpag");
		
		File parent = file.getParentFile();
		File contentFile = new File(parent, b.toString());
		
		if(contentFile.exists()) {
			page.setPageStream(new FileInputStream(file));
		}
		
		return page;
	}

	private Reader getReader(File file) throws FileNotFoundException {
		return new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
	}
	
	@Override
	public void write(File file, FileMetadata metadata, Object value) throws FileNotFoundException, IOException {
		
		try(Writer stream = getWriter(file)){
			gson.toJson(value, Object.class, stream);
			stream.flush();
		}
		
		if(value == null) {
			return;
		}
		
		Page page = (Page)value;

		if(page.getPageStream() == null) {
			return;
		}
		
		Locale locale = (Locale) metadata.getExtMetadata("locale");
		
		StringBuilder b = new StringBuilder(metadata.getName());
		
		if(locale != null) {
			b.append("_").append(locale.getLanguage()).append("_").append(locale.getCountry());
		}
		b.append(".cpag");
		
		File parent = file.getParentFile();
		File contentFile = new File(parent, b.toString());
		
		if(contentFile.exists()) {
			page.setPageStream(new FileInputStream(file));
		}
		
	}

	public Writer getWriter(File file) throws FileNotFoundException {
		return new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
	}
	
}
