package br.com.uoutec.community.ediacaran.front.page;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public class Page {

	private String title;
	
	private Map<String, String> header;
	
	private transient Reader content;

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

	public Reader getContent() {
		return content;
	}

	public void setContent(Reader content) {
		this.content = content;
	}
	
	public void write(Writer writer) throws IOException {
		char[] buf = new char[4096];
		int l;
		
		try{
			while((l = content.read(buf, 0, buf.length)) > 0 ) {
				writer.write(buf, 0, l);
			}
		}
		finally {
			content.close();
		}
	}
	
	protected void finalize() throws Throwable {
		try {
			content.close();
		}
		finally{
			super.finalize();
		}
	}
}
