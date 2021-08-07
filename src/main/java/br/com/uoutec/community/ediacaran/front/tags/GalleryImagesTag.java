package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="gallery-images", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class GalleryImagesTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/gallery-images";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String galleryID;
	
	private JspFragmentVarParser content;
	
	public GalleryImagesTag() {
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

	@TagAttribute
	public void setGalleryID(String galleryID) {
		this.galleryID = galleryID;
	}
    
}
