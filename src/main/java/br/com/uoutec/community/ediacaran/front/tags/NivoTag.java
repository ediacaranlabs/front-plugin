package br.com.uoutec.community.ediacaran.front.tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.theme.TemplateListVarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;

public class NivoTag extends AbstractPanelComponent {

	private static final long serialVersionUID = 748182107582888257L;

	private static final String EMPTY_IMG_ALT = "";
	
	private static final String NIVO_IMAGE = "/components/nivo-image";
	
	private static final String NIVO_CAPTION = "/components/nivo-caption";

	public static final String TEMPLATE  = "/components/nivo";
	
	private List<Object[]> images;
	
	private List<Object[]> caption;
	
	private String button;
	
	public NivoTag() {
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
	
	public void beforePrepareVars(Map<String, Object> vars) {
		String packageName = getPackageTheme();
		Theme theme = getTheme();
		vars.put("images", new TemplateListVarParser(NIVO_IMAGE, packageName, this, theme, images));
		vars.put("captions", new TemplateListVarParser(NIVO_CAPTION, packageName, this, theme, caption));
	}
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

}
