package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="landing-section", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class LandingSectionTag  extends AbstractSimpleComponent {

	public static final String TEMPLATE  = "/components/landing-section";
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String name;
	
	private String title;
	
	private String img;
	
	private String align;
	
	public LandingSectionTag() {
	}
	
    protected TagComponent createTagComponent() {
    	return new TagComponent() {
    		
    		public void afterPrepareVars(Map<String, Object> vars) {
    			vars.put("align-left",  "right".equals(align)? " col-lg-offset-1 col-sm-push-6" : "" );
    			vars.put("align-right", "right".equals(align)? " col-lg-offset-2" : "col-sm-pull-6" );
    		}
    		
    	};
    }
	
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }

	public String getName() {
		return name;
	}

	@TagAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	@TagAttribute
	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	@TagAttribute
	public void setImg(String img) {
		this.img = img;
	}

	public String getAlign() {
		return align;
	}

	@TagAttribute
	public void setAlign(String align) {
		this.align = align;
	}

}
