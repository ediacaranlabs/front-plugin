package br.com.uoutec.community.ediacaran.front.page;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import br.com.uoutec.application.SystemProperties;
import br.com.uoutec.application.io.Path;
import br.com.uoutec.application.io.Vfs;

public class Page {

	private String title;
	
	private Map<String, String> header;
	
	private List<BreadcrumbPath> breadcrumb;

	private String template;

	private transient Reader content;

	private String thumbnailTitle;
	
	private String keywords;
	
	private String thumbnailDescription;
	
	private transient Path thumbnail;
	
	public List<BreadcrumbPath> getBreadcrumb() {
		return breadcrumb;
	}

	public void setBreadcrumb(List<BreadcrumbPath> breadcrumb) {
		this.breadcrumb = breadcrumb;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getThumbnailTitle() {
		return thumbnailTitle;
	}

	public void setThumbnailTitle(String thumbnailTitle) {
		this.thumbnailTitle = thumbnailTitle;
	}

	public String getThumbnailDescription() {
		return thumbnailDescription;
	}

	public void setThumbnailDescription(String thumbnailDescription) {
		this.thumbnailDescription = thumbnailDescription;
	}

	public Path getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Path thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getPublicThumbnailPath() {
		
		if(thumbnail != null) {
			String baseWebApp = SystemProperties.getProperty("app.web");
			Path fileBaseWebApp = Vfs.getPath(baseWebApp);
			String relative = fileBaseWebApp.getRelativePath(thumbnail).getFullName();
			relative = relative.replaceAll("\\+", "/");
			return "/" + relative;
		}
		
		return null;
	}
	
	public void write(Writer writer) throws IOException {
		
		if(content == null) {
			return;
		}
		
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
