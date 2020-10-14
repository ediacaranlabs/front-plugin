package br.com.uoutec.community.ediacaran.front.tags;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.PageContext;

import br.com.uoutec.community.ediacaran.front.PluginInstaller;
import br.com.uoutec.community.ediacaran.front.TemplateVarParser;
import br.com.uoutec.community.ediacaran.front.tema.Tema;
import br.com.uoutec.community.ediacaran.front.tema.TemaRegistry;

public class BreadcrumbPathTag extends AbstractSimpleTag {

	public static final String TEMPLATE = "/bootstrap4/components/breadcrumb-path";
	
	public static final String TEMPLATE_ICON = "/bootstrap4/components/icon";
	
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
			add("icon");
			add("text");
			add("lnk");
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
				put("icon", new AttributeParserImp() {
					
					@Override
					public Object toValue(Object value, Object component) {
						BreadcrumbPathTag tag     = (BreadcrumbPathTag)component;
						TemaRegistry temaRegistry = (TemaRegistry)tag.getJspContext().getAttribute(PluginInstaller.TEMA_REGISTRY, PageContext.APPLICATION_SCOPE);
				    	Tema tema = temaRegistry.getCurrentTema();
				    	String packageName = (String)tag.getProperty(SetTemplatePackageTag.PACKAGE_NAME);

				    	return value == null? null : new TemplateVarParser(TEMPLATE_ICON, packageName, tema).put("icon", value).put("size", 1);
					}
				});
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String icon;
	
	private String text;
	
	private String lnk;
	
	public BreadcrumbPathTag() {
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLnk() {
		return lnk;
	}

	public void setLnk(String lnk) {
		this.lnk = lnk;
	}
    
}
