package br.com.uoutec.community.ediacaran.front.tags;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.uoutec.community.ediacaran.front.pub.DataUtil;

public class ReadData {

	private static final Logger logger = LoggerFactory.getLogger(ReadData.class);
	
	
	@SuppressWarnings("unchecked")
	public static Map<Object,Object> loadData(String path, File base, File root, Locale locale) throws IOException{
		
		File fData              = new File(base, path + ".dpag");
		Map<Object,Object> data = loadData(fData, root, locale);
		
		if(data != null && locale != null) {
			String localePath = path + "_" + locale.toString() + ".dpag";
			File localeFile = new File(base, localePath);
			Map<Object,Object> localeData = loadData(localeFile, root, locale);
			
			if(localeData != null) {
				merge(localeData, data, null);
			}
		}
		
		if(data != null) {
			List<Object> inc = (List<Object>)data.get("includes");
			
			Map<Object,Object> vars = new HashMap<Object,Object>();

			if(inc != null) {
				List<Map<Object,Object>> incData = loadIncludes(inc, fData.getParentFile().getCanonicalFile(), root, locale);
				
				for(Map<Object,Object> i: incData) {
					merge(i, vars, null);
				}

			}
			
			merge(vars, data, null);
		}
		
		return data;
	}
	
	private static Map<Object,Object> loadData(File file, File root, Locale locale) throws IOException{
		
		if(!file.exists() || !file.isFile() || !file.canRead()) {
			return null;
		}
		
		return getData(file, root);
	}
	
	private static List<Map<Object,Object>> loadIncludes(List<Object> includes, File basePath, File root, Locale locale) throws IOException{
		
		List<Map<Object,Object>> incData = new ArrayList<Map<Object,Object>>();
		
		for(Object i: includes) {
			Map<Object,Object> data = loadData(String.valueOf(i), basePath, root, locale);
			if(data != null) {
				incData.add(data);
			}
		}

		return incData;
	}
	
	/*
	public static Map<Object,Object> loadData(String path, Locale locale, File root, File requestFile) throws IOException{
		File baseFile			= root.equals(requestFile)? requestFile : requestFile.getParentFile();
		File fData              = new File(baseFile, path + ".dpag");
		Map<Object,Object> data = loadData(fData, root);
		
		if(data != null && locale != null) {
			String localePath = path + "_" + locale.toString() + ".dpag";
			File localeFile = new File(baseFile, localePath);
			Map<Object,Object> localeData = loadData(localeFile, root);
			
			if(localeData != null) {
				merge(localeData, data, null);
			}
		}
		return data;
	}
	 */
	
	/*
	public static Map<Object,Object> loadData(String path, File base, File root, Locale locale) throws IOException{
		
		File fData              = new File(base, path + ".dpag");
		Map<Object,Object> data = loadData(fData, root, locale);
		
		if(data != null && locale != null) {
			String localePath = path + "_" + locale.toString() + ".dpag";
			File localeFile = new File(base, localePath);
			Map<Object,Object> localeData = loadData(localeFile, root, locale);
			
			if(localeData != null) {
				merge(localeData, data, null);
			}
		}
		return data;
	}
	
	private static Map<Object,Object> loadData(File file, File root, Locale locale) throws IOException{
		
		if(!file.exists() || !file.isFile() || !file.canRead()) {
			return null;
		}
		
		Map<Object,Object> data = getData(file, root);
		
		if(data != null) {
			List<Object> inc = (List<Object>)data.get("includes");
			
			Map<Object,Object> vars = new HashMap<Object,Object>();

			if(inc != null) {
				List<Map<Object,Object>> incData = loadIncludes(inc, file.getParentFile().getCanonicalFile(), root, locale);
				
				for(Map<Object,Object> i: incData) {
					merge(i, vars, null);
				}

			}
			
			merge(data, vars, null);
			return vars;
		}
		
		return data;
	}

	private static List<Map<Object,Object>> loadIncludes(List<Object> includes, File basePath, File root, Locale locale) throws IOException{
		
		List<Map<Object,Object>> incData = new ArrayList<Map<Object,Object>>();
		
		for(Object i: includes) {
			Map<Object,Object> data = loadData(String.valueOf(i), basePath, root, locale);
			if(data != null) {
				incData.add(data);
			}
		}

		return incData;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Map<Object,Object>> loadIncludes(Map<Object,Object> data, File root, File basepath) throws IOException{
		
		List<Object> inc = (List<Object>)data.get("includes");
		
		List<Map<Object,Object>> incData = new ArrayList<Map<Object,Object>>();
		
		if(inc != null) {
			
			for(Object i: inc) {
				
				File fInc = new File(basepath, String.valueOf(i));
				Map<Object,Object> dataInc = getData(fInc, root);
				
				if(dataInc != null) {
					incData.add(dataInc);
				}
				
			}
			
		}
		
		return incData;
	}
	*/
	
	@SuppressWarnings("unchecked")
	private static void merge (Map<Object, Object> from, Map<Object, Object> to, String prefix){
		
		for(Entry<Object, Object> vals: from.entrySet()) {
			Object key = vals.getKey();
			Object val = vals.getValue();
			
			if("includes".equals(key)) {
				continue;
			}
			
			Object oldVal = to.get(key);
			
			if(val instanceof Map && oldVal instanceof Map) {
				merge((Map<Object, Object>)val, (Map<Object, Object>)oldVal, String.valueOf(key) + "." );
			}
			else {
				if(logger.isTraceEnabled()) {
					logger.trace("merge {} : {}", String.valueOf(key), val);
				}
				to.put(key, val);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static Map<Object,Object> getData(File dta, File root) throws IOException{
		
		dta = dta.getCanonicalFile();
		
		if(!dta.getAbsolutePath().startsWith(root.getAbsolutePath())) {
			return null;
		}
		
		if(!dta.exists() || !dta.canRead()) {
			return null;
		}
		
		String text = readData(dta);
		return (Map<Object, Object>) DataUtil.decode(text);
	}
	
	private static String readData(File f) throws IOException {
		byte[] b = new byte[2048];
		int l;
		
		StringBuilder builder = new StringBuilder();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(f);
			
			while((l = fin.read(b)) != -1) {
				builder.append(new String(b, 0, l));
			}
			return builder.toString();
		}
		finally {
			if(fin != null) {
				fin.close();
			}
		}
	}
	
}
