package br.com.uoutec.community.ediacaran.front.tags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import br.com.uoutec.community.ediacaran.front.TemplateListVarParser;
import br.com.uoutec.community.ediacaran.front.TemplateVarParser;

public class NivoTag extends AbstractBodyTag {

	private static final long serialVersionUID = 748182107582888257L;

	private static final String EMPTY_IMG_ALT = "";
	
	private static final String NIVO_IMAGE = "/bootstrap4/components/nivo-image";
	
	private static final String NIVO_CAPTION = "/bootstrap4/components/nivo-caption";

	public static final String TEMPLATE  = "/bootstrap4/components/nivo";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_ATTRS) {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> ATTRIBUTE_PARSERS = 
		Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_ATTRIBUTE_PARSERS){{
		}});

	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>(AbstractSimpleTag.DEFAULT_PROPS) {{
			add("button");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
			}});
	
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
	
	public int doAfterBody() {

		new TemplateVarParser(TEMPLATE, getTemaPackage(), getTema())
			.put("images", new TemplateListVarParser(NIVO_IMAGE, images))
			.put("captions", new TemplateListVarParser(NIVO_CAPTION, caption))
			.parse(bodyContent.getEnclosingWriter());

		return SKIP_BODY;
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
	
	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

}
