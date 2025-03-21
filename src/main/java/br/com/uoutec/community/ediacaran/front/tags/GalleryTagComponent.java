package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="gallery", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class GalleryTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/gallery";
	
	public static final String PARENT = GalleryTagComponent.class.getSimpleName() + ":PARENT";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private Integer cols;
	
	private int nextImage;
	
	public GalleryTagComponent() {
		this.nextImage = 1;
	}
	
	public int getNextImageID() {
		return nextImage++;
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    	    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    	    		Writer out) throws IOException {
    	    	setProperty(PARENT, GalleryTagComponent.this);
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

	@Override
	public String getType() {
		return "gallery";
	}
	
}
