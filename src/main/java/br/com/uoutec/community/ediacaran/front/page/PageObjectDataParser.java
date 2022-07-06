package br.com.uoutec.community.ediacaran.front.page;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PageObjectDataParser{

	public Map<String,Object> toData(Page page) {
		Map<String,Object> o = new HashMap<String,Object>();
		o.put("title", page.getTitle());
		o.put("template", page.getTemplate());
		
		Map<String,String> header = page.getHeader();
		List<BreadcrumbPath> breadcrumb = page.getBreadcrumb();
		
		if(header != null) {
			Map<String,Object> headerOBJ = new HashMap<String,Object>();
			header.entrySet().stream().forEach(e->headerOBJ.put(e.getKey(), e.getValue()));
			o.put("header", headerOBJ);
		}
		
		if(breadcrumb != null) {
			List<Map<String,Object>> breadcrumbDTA = new LinkedList<Map<String,Object>>();
			breadcrumb.stream().forEach((e)->{
				Map<String,Object> rc = new HashMap<String,Object>();
				rc.put("name", e.getName());
				rc.put("link", e.getLink());
				rc.put("icon", e.getIcon());
				breadcrumbDTA.add(rc);
			});
			
			o.put("breadcrumb", breadcrumbDTA);
		}
		
		return o;
	}
	
}
