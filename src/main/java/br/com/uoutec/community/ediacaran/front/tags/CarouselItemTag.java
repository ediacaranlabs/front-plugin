package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;;

public class CarouselItemTag extends BasicTag {

	public static final String TEMPLATE   = "bootstrap4/templates/components/carousel-item";

	private String lnk;
	
	private String img;
	
    public void doTag() throws JspException, IOException{
    	
    	try {
			Map<String,Object> vars = new HashMap<String,Object>();
			vars.put("lnk", lnk);
			vars.put("img", img);
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
			
    	}
    	catch(IllegalStateException e) {
    		throw e;
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

	public String getLnk() {
		return lnk;
	}

	public void setLnk(String lnk) {
		this.lnk = lnk;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
