package br.com.uoutec.community.ediacaran.front.page;

import java.util.Map;

import br.com.uoutec.community.ediacaran.system.repository.ObjectHandler;

public class PageObjectHandler implements ObjectHandler{

	private DataPageObjectParser dataPageObjectParser;
	
	private PageObjectDataParser pageObjectDataParser;
	
	public PageObjectHandler() {
		this.dataPageObjectParser = new DataPageObjectParser();
		this.pageObjectDataParser = new PageObjectDataParser();
	}
	@Override
	public String getType() {
		return "menu";
	}

	@Override
	public boolean accept(Object o) {
		return true;
	}

	@Override
	public boolean accept(String type) {
		return "menu".equals(type);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object toObject(Object data) {
		return dataPageObjectParser.toObject((Map<String, Object>) data);
	}
	
	@Override
	public Object toData(Object object) {
		return pageObjectDataParser.toData((Page) object);
	}

}
