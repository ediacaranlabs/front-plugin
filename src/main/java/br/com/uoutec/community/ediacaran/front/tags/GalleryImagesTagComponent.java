package br.com.uoutec.community.ediacaran.front.tags;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="gallery-images", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class GalleryImagesTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/gallery-images";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String galleryID;
	
	public GalleryImagesTagComponent() {
	}
	
	protected void beforeBuildComponent(Component component) {
		if(galleryID == null) {
			galleryID = ((GalleryTagComponent)getParentTag()).getId();
		}
	}
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
    
	public String getGalleryID() {
		return galleryID;
	}

	@TagAttribute
	public void setGalleryID(String galleryID) {
		this.galleryID = galleryID;
	}
    
	@Override
	public String getType() {
		return "gallery-images";
	}
	
}
