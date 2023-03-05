package br.com.uoutec.community.ediacaran.front.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPageObjectParser {

	@SuppressWarnings("unchecked")
	public Page toObject(Map<String,Object> data) {
		
		Page page = new Page();
		page.setTitle((String) data.get("title"));
		
		page.setTemplate((String) data.get("template"));
		
		page.setThumbnailTitle((String) data.get("thumbnailTitle"));
		page.setThumbnailDescription((String) data.get("thumbnailDescription"));

		Map<String,Object> headerMAP = (Map<String,Object>) data.get("header");

		List<Map<String,Object>> breadcrumbMAP = (List<Map<String,Object>>) data.get("breadcrumb");
		
		if(headerMAP != null) {
			Map<String,String> header = new HashMap<String,String>();
			headerMAP.entrySet()
				.stream()
				.forEach(e->header.put(e.getKey(), String.valueOf(e.getValue())));
			page.setHeader(header);
		}

		if(breadcrumbMAP != null) {
			List<BreadcrumbPath> breadcrumb = new ArrayList<BreadcrumbPath>();
			breadcrumbMAP.stream().forEach((e)->{
				BreadcrumbPath bcp = new BreadcrumbPath();
				bcp.setIcon((String) e.get("icon"));
				bcp.setLink((String) e.get("link"));
				bcp.setName((String) e.get("name"));
				breadcrumb.add(bcp);
			});
			
			page.setBreadcrumb(breadcrumb);
		}
		
		return page;
	}
	
}
