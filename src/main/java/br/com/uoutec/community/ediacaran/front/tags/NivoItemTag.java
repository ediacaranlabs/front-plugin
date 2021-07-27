package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;

//@Tag(value="nivo-slider", bodyType=BodyTypes.SCRIPTLESS)
//@TagDoc({
//"	Adiciona um novo NIVO slider. Os slides podem ser adicionados com <nivo-slider-item/>",
//"	Ex:",
//"	<nivo-slider>",
//"		<nivo-slider-item image=\"/imgs/slide1.png\" tile=\"Title1\">Text Content</nivo-slider>",
//"	</nivo-slider>"
//})
public class NivoItemTag extends AbstractSimpleComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String image;
	
	private String title;
	
	private String link;
	
    public void doTag() throws JspException, IOException{
    	
    	Object parent = super.getParentTag();
    	
    	if(parent instanceof NivoTag) {
    		StringWriter stringWriter = new StringWriter();
    		if(this.getJspBody() != null) {
    			this.getJspBody().invoke(stringWriter);
    		}
    		((NivoTag)parent).add(this.image, this.title, this.link, stringWriter.toString());
    	}
    	
    }

    protected String getDefaultTemplate() {
    	return null;
    }
    
	public String getLink() {
		return link;
	}

//	@TagDoc("Define o link da imagem")
//	@TagAttribute(required=true,type=String.class,fragment=true)
	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
