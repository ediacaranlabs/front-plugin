package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

public class GalleryImageTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/gallery-image";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/

	private String title;
	
	private String filter;
	
	private String icon;
	
	private String src;
			
	private String text;
	
	public GalleryImageTag() {
	}
	
	protected void afterPrepareVars(Map<String, Object> vars) {
		GalleryTag tag = (GalleryTag) getJspContext().getAttribute(GalleryTag.PARENT);
		vars.put("cols",  tag.getCols());
		vars.put("id",    tag.getId());
		vars.put("index", tag.getNextImageID());
	}

	protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    
}
