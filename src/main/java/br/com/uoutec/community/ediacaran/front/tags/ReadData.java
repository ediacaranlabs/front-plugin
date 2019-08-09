package br.com.uoutec.community.ediacaran.front.tags;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.pub.DataUtil;

public class ReadData {

	public static Map<Object,Object> loadData(String path, File basepath) throws IOException{
		
		File fData              = new File(basepath, path);
		Map<Object,Object> data = getData(fData, basepath);
		
		if(data != null) {
			
			List<Map<Object,Object>> incData = loadIncludes(data, basepath);
			
			Map<Object,Object> vars = new HashMap<Object,Object>();
			
			for(Map<Object,Object> i: incData) {
				vars.putAll(i);
			}
			
			vars.putAll(data);
			return vars;
		}
		
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	private static List<Map<Object,Object>> loadIncludes(Map<Object,Object> data, File basepath) throws IOException{
		
		List<Object> inc = (List<Object>)data.get("includes");
		
		List<Map<Object,Object>> incData = new ArrayList<Map<Object,Object>>();
		
		if(inc != null) {
			
			for(Object i: inc) {
				
				File fInc = new File(basepath, String.valueOf(i));
				Map<Object,Object> dataInc = getData(fInc, basepath);
				
				if(dataInc != null) {
					incData.add(dataInc);
				}
				
			}
			
		}
		
		return incData;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<Object,Object> getData(File dta, File basepath) throws IOException{
		
		dta = dta.getCanonicalFile();
		
		if(!dta.getAbsolutePath().startsWith(basepath.getAbsolutePath())) {
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
