package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class GalleryFiltersTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/gallery-filters";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String galleryID;
	
	private JspFragmentVarParser content;
	
	public GalleryFiltersTag() {
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
		if(galleryID == null) {
			galleryID = ((GalleryTag)super.getParentTag()).getId();
		}
		
		this.content = new JspFragmentVarParser(getJspBody());
    }
    
	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}

	public String getGalleryID() {
		return galleryID;
	}

	public void setGalleryID(String galleryID) {
		this.galleryID = galleryID;
	}
    
}
