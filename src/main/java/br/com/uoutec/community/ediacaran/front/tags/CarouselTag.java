package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

public class CarouselTag extends AbstractTag {

	public static final String TEMPLATE = "/bootstrap4/templates/components/carousel";
	
	public CarouselTag() {
	}
	
    public void doInnerTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("attr",           super.toAttrs());
			vars.put("accordionID",    this.getId());
			vars.put("carousel-itens", new JspFragmentVarParser(getJspBody()));
			
			TemplatesManager.getTemplatesManager()
				.apply(this.getTemplate() == null? TEMPLATE : this.getTemplate(), vars, getJspContext().getOut());
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
    
}
