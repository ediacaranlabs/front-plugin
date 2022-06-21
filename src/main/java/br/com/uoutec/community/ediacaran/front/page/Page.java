package br.com.uoutec.community.ediacaran.front.page;

import java.io.InputStream;
import java.util.Map;

public class Page {

	private String title;
	
	private Map<String, String> header;
	
	private InputStream pageStream;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public InputStream getPageStream() {
		return pageStream;
	}

	public void setPageStream(InputStream pageStream) {
		this.pageStream = pageStream;
	}
	
}
