package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;

public class NivoItemTag extends AbstractSimpleComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String image;
	
	private String title;
	
	private String link;
	
    public void doTag() throws JspException, IOException{
    	
    	JspTag parent = super.getParent();
    	
    	if(parent instanceof NivoTag) {
    		StringWriter stringWriter = new StringWriter();
    		this.getJspBody().invoke(stringWriter);
    		((NivoTag)parent).add(this.image, this.title, this.link, stringWriter.toString());
    	}
    	
    }

    protected String getDefaultTemplate() {
    	return null;
    }
    
	public String getLink() {
		return link;
	}

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
