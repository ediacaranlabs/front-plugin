package br.com.uoutec.community.ediacaran.front.tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;

@Tag(
	name="nivo-slider", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
	)
public class NivoTagComponent extends AbstractBodyTagComponent {

	private static final long serialVersionUID = 748182107582888257L;

	private static final String EMPTY_IMG_ALT = "";
	
	private static final String NIVO_IMAGE = "/components/nivo-image";
	
	private static final String NIVO_CAPTION = "/components/nivo-caption";

	public static final String TEMPLATE  = "/components/nivo";
	
	private List<Object[]> images;
	
	private List<Object[]> caption;
	
	private String button;
	
	public NivoTagComponent() {
	}
	
	public void add(String img, String title, String link, String content) {
		images.add(new Object[] {img, EMPTY_IMG_ALT, images.size()});
		caption.add(new Object[] {caption.size(), title, content, link, button == null? "Read more" : button });
	}
	
    public int doStartTag() throws JspException {
		this.images  = new ArrayList<Object[]>();
		this.caption = new ArrayList<Object[]>();
        return EVAL_BODY_BUFFERED;
    }


    public int doEndTag() throws JspException {
		this.images  = null;
		this.caption = null;
    	return super.doEndTag();
    }
	
    protected Component createComponent() {
    	return new Component() {
    		
    		public void beforePrepareVars(Map<String, Object> vars) {
    			String packageName = getPackageTheme();
    			Theme theme = getTheme();
    			vars.put("images", new TemplateListVarParser(NIVO_IMAGE, packageName, this, theme, images));
    			vars.put("captions", new TemplateListVarParser(NIVO_CAPTION, packageName, this, theme, caption));
    		}
    		
    	};
    }
    
    public String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getButton() {
		return button;
	}

	@TagAttribute
	public void setButton(String button) {
		this.button = button;
	}

}
