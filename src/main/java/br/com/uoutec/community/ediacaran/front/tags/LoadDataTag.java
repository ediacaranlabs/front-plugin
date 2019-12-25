package br.com.uoutec.community.ediacaran.front.tags;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.application.se.ApplicationBootstrapProvider;
import br.com.uoutec.community.ediacaran.ServerBootstrap;
import br.com.uoutec.community.ediacaran.front.TemplatesManagerException;

public class LoadDataTag  extends AbstractSimpleTag {

	public static final String TEMPLATE  = "";
	
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
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(AbstractSimpleTag.DEFAULT_PROPERTY_PARSERS){{
			}});
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String file;
	
	private String var;
	
	public LoadDataTag() {
	}
	
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	
    	ServerBootstrap sb     = (ServerBootstrap) ApplicationBootstrapProvider.getBootstrap();
    	Map<Object,Object> dta = ReadData.loadData(file, new File(sb.getWebapp(), super.getRequestPath()));
    	super.getJspContext().setAttribute(var == null? "vars" : var, dta);
	}
	
    protected void applyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException, TemplatesManagerException {
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
