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
	
	private JspFragmentVarParser content;
	
	public GalleryTag() {
		this.nextImage = 1;
	}
	
	public int getNextImageID() {
		return nextImage++;
	}
	
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	//check if PARENT exists
    	getJspContext().setAttribute(PARENT, this);
    }
    
    protected void afterApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	getJspContext().removeAttribute(PARENT);
    }

    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
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

	public JspFragmentVarParser getContent() {
		return content;
	}

	public void setContent(JspFragmentVarParser content) {
		this.content = content;
	}
    
}
