package br.com.uoutec.community.ediacaran.front.page;

import java.io.Reader;
import java.util.Map;

public class Page {

	private String title;
	
	private Map<String, String> header;
	
	private transient Object content;

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

	public Object getContent() {
		return content;
	}

	public void setContent(Reader content) {
		this.content = content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
