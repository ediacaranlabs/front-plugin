package br.com.uoutec.community.ediacaran.front.page;

import java.util.HashMap;
import java.util.Map;

public class PageObjectDataParser{

	public Map<String,Object> toData(Page page) {
		Map<String,Object> o = new HashMap<String,Object>();
		o.put("title", page.getTitle());
		
		Map<String,String> header = page.getHeader();
		
		if(header != null) {
			Map<String,Object> headerOBJ = new HashMap<String,Object>();
			header.entrySet().stream().forEach(e->headerOBJ.put(e.getKey(), e.getValue()));
			o.put("header", headerOBJ);
		}
		return o;
	}
	
}
