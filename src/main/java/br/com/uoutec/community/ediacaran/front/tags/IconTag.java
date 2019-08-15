package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class IconTag extends AbstractTag {

	public static final String TEMPLATE       = "/bootstrap4/templates/components/icon";
	
	public static final String TEMPLATE_STACK = "/bootstrap4/templates/components/icon-stack";
	
	private Integer size;
	
	private Integer bgSize;
	
	private Integer iconSize;
	
	private String bg;
	
	private String name;
	
	public IconTag() {
	}
	
    public void doInnerTag() throws JspException, IOException{
    	
    	try {
			size     = size == null? 3 : size;
			bgSize   = bgSize == null? size - 1 : bgSize;
			iconSize = iconSize == null? bgSize - 1 : iconSize;
			
			Map<String, Object> vars = new HashMap<String, Object>();
			
			vars.put("size", size);
			vars.put("bg-size", bgSize);
			vars.put("bg", bg);
			vars.put("icon", name);
			vars.put("icon-size", iconSize);
			vars.put("attr", super.toAttrs());
			
			TemplatesManager.getTemplatesManager()
				.apply(
					this.getTemplate() == null? 
							bg != null? TEMPLATE_STACK : TEMPLATE : 
							this.getTemplate(), 
					vars, 
					getJspContext().getOut()
				);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }

    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }

    protected Set<String> getDefaultAttributes(){
    	return DEFAULT_ATTRS;
    }

    protected Set<String> getEmptyAttributes(){
    	return DEFAULT_EMPTY_ATTRIBUTES;
    }
    
    protected Map<String, AttributeParser> getAttributeParsers(){
    	return DEFAULT_ATTRIBUTE_PARSERS;
    }

    protected Set<String> getDefaultProperties(){
    	return DEFAULT_PROPS;
    }

    protected Map<String, AttributeParser> getPropertyParsers(){
    	return DEFAULT_PROPERTY_PARSERS;
    }
    
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getBgSize() {
		return bgSize;
	}

	public void setBgSize(Integer bgSize) {
		this.bgSize = bgSize;
	}

	public Integer getIconSize() {
		return iconSize;
	}

	public void setIconSize(Integer iconSize) {
		this.iconSize = iconSize;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
