package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;

@Tag(
	name="nivo-slider-item", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class NivoItemTagComponent extends AbstractSimpleTagComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String image;
	
	private String title;
	
	private String link;
	
    public void doTag() throws JspException, IOException{
    	
    	Object parent = super.getParentTag();
    	
    	if(parent instanceof NivoTagComponent) {
    		StringWriter stringWriter = new StringWriter();
    		if(this.getJspBody() != null) {
    			this.getJspBody().invoke(stringWriter);
    		}
    		((NivoTagComponent)parent).add(this.image, this.title, this.link, stringWriter.toString());
    	}
    	
    }

    public String getDefaultTemplate() {
    	return null;
    }
    
	public String getLink() {
		return link;
	}

	@TagAttribute(required=true)
	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	@TagAttribute(required=true)
	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	@TagAttribute(required=true)
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getType() {
		return "nivo-item";
	}
	
}
