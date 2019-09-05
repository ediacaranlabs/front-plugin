package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import br.com.uoutec.community.ediacaran.front.StringPattern;

public class NivoTag extends AbstractBodyTag {

	private static final long serialVersionUID = 748182107582888257L;

	private static final String NIVO_IMAGE = "/bootstrap4/templates/components/nivo-image";
	
	private static final StringPattern NIVO_CAPTION_TEMPLATE = new StringPattern(
			"						<div class=\"nivo-caption\" id=\"caption-{count}\">\r\n" + 
			"							<div>\r\n" + 
			"								<h2>\r\n" + 
			"									<strong>{title}</strong>\r\n" + 
			"								</h2>\r\n" + 
			"								<p>{content}</p>\r\n" + 
			"								<a href=\"{link}\" class=\"btn btn-nivo\">{btn_message}</a>\r\n" + 
			"							</div>\r\n" + 
			"						</div>\r\n"
		);

	public static final String TEMPLATE  = "/bootstrap4/templates/components/gallery-filter";
	
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
			add("bgImage");
			add("content");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
				put("bgImage", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return "background: url(" + value + ") no-repeat center center; background-size: cover;";
					}
					
				});
				
				put("content", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						return new JspFragmentVarParser(((IntroTag)component).getJspBody());
					}
					
				});
				
			}});
	
	private List<Object[]> itens;

	private String button;
	
	public NivoTag() {
		this.itens = new ArrayList<Object[]>();
	}
	
	public int doAfterBody() {

		try {
			StringBuilder images = new StringBuilder();
			
			int i=0;
			for (Object[] item : itens) {
				images.append(NIVO_IMAGE_TEMPLATE.toString(item[0], "", i++));
			}

			StringBuilder caption = new StringBuilder();
			
			i=0;
			for (Object[] item : itens) {
				caption.append(NIVO_CAPTION_TEMPLATE.toString(i++, item[1], item[3], item[2], button == null? "Read more" : button));
			}

			JspWriter out = bodyContent.getEnclosingWriter();
			out.write(NIVO_TEMPLATE.toString(images,caption));
			
			
			new TemplateVarParser(TEMPLATE)
				.put(
					"images", new TemplateVarParser(NIVO_IMAGE)
				);

		} 
		catch (IOException e) {
			throw new IllegalStateException(e);
		}

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

	public List<Object[]> getItens() {
		return itens;
	}
	
}
