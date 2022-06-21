package br.com.uoutec.community.ediacaran.front.page;

import java.util.HashMap;
import java.util.Map;

public class DataPageObjectParser {

	@SuppressWarnings("unchecked")
	public Page toObject(Map<String,Object> data) {
		
		Page page = new Page();
		page.setTitle((String) data.get("title"));
		
		Map<String,Object> headerMAP = (Map<String,Object>) data.get("header");
		
		if(headerMAP != null) {
			Map<String,String> header = new HashMap<String,String>();
			headerMAP.entrySet()
				.stream()
				.forEach(e->header.put(e.getKey(), String.valueOf(e.getValue())));
			page.setHeader(header);
		}
				
		return page;
	}
	
}
