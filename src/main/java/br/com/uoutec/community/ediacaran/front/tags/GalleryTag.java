package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="gallery", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class GalleryTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/gallery";
	
	public static final String PARENT = GalleryTag.class.getSimpleName() + ":PARENT";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer cols;
	
	private int nextImage;
	
	public GalleryTag() {
		this.nextImage = 1;
	}
	
	public int getNextImageID() {
		return nextImage++;
	}
	
    protected TagComponent createTagComponent() {
    	return new TagComponent() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	setProperty(PARENT, this);
    	    }
    	    
    	    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	setProperty(PARENT, null);
    	    }
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public Integer getCols() {
		return cols;
	}

	@TagAttribute
	public void setCols(Integer cols) {
		this.cols = cols == null? 1 : (int)(12.0/cols.doubleValue() < 1? 1 : 12.0/cols.doubleValue());
	}

	public int getNextImage() {
		return nextImage;
	}

	public void setNextImage(int nextImage) {
		this.nextImage = nextImage;
	}

}
