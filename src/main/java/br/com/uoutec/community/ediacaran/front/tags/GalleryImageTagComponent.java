package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="gallery-image", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class GalleryImageTagComponent  extends AbstractSimpleTagComponent {

	public static final String TEMPLATE  = "/components/gallery-image";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/

	private String title;
	
	private String filter;
	
	private String icon;
	
	private String src;
			
	private String text;
	
	public GalleryImageTagComponent() {
	}
	
    protected Component createComponent() {
    	return new Component() {
    		
    		protected void afterPrepareVars(Map<String, Object> vars) {
    			GalleryTagComponent tag = (GalleryTagComponent) getJspContext().getAttribute(GalleryTagComponent.PARENT);
    			vars.put("cols",  tag.getCols());
    			vars.put("id",    tag.getId());
    			vars.put("index", tag.getNextImageID());
    		}
    		
    	};
    }
	
	public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getTitle() {
		return title;
	}

	@TagAttribute(required=true)
	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilter() {
		return filter;
	}

	@TagAttribute
	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getIcon() {
		return icon;
	}

	@TagAttribute(required=true)
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSrc() {
		return src;
	}

	@TagAttribute(required=true)
	public void setSrc(String src) {
		this.src = src;
	}

	public String getText() {
		return text;
	}

	@TagAttribute
	public void setText(String text) {
		this.text = text;
	}
    
	@Override
	public String getType() {
		return "gallery-image";
	}
	
}
