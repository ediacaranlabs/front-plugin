package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="gallery-images", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class GalleryImagesTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/gallery-images";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String galleryID;
	
	public GalleryImagesTagComponent() {
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
    protected Component createComponent() {
    	return new Component() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String, Object> vars, Writer out) throws IOException {
    			if(galleryID == null) {
    				galleryID = ((GalleryTagComponent)super.getParentTag()).getId();
    			}
    	    }
    		
    	};
    }
    
	public String getGalleryID() {
		return galleryID;
	}

	@TagAttribute
	public void setGalleryID(String galleryID) {
		this.galleryID = galleryID;
	}
    
}
