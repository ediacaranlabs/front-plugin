package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class NivoItemTag extends SimpleTagSupport {

	private String image;
	
	private String title;
	
	private String link;
	
    public void doTag() throws JspException, IOException{
    	
    	JspTag parent = super.getParent();
    	
    	if(parent instanceof NivoTag) {
    		StringWriter sw = new StringWriter();
    		
    		getJspBody().invoke(sw);
    		
    		((NivoTag)parent)
    			.getItens().add(new Object[] {
    				this.image,
    				this.title,
    				this.link,
    				sw
    			});
    	}
    	
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
