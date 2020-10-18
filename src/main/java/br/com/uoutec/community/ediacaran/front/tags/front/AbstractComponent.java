package br.com.uoutec.community.ediacaran.front.tags.front;

import java.io.File;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginData;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParser;
import br.com.uoutec.community.ediacaran.system.tema.AttributeParserImp;
import br.com.uoutec.community.ediacaran.system.tema.Component;
import br.com.uoutec.community.ediacaran.system.tema.TagTemplate;
import br.com.uoutec.community.ediacaran.system.tema.TemaException;
import br.com.uoutec.community.ediacaran.system.tema.TemplateLoader;

public abstract class AbstractComponent implements Component{

	protected String TEMPLATE;
	
	@SuppressWarnings("serial")
	protected Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("id");
			add("classStyle");
		}});

	protected Set<String> DEFAULT_EMPTY_ATTRIBUTES = 
			Collections.unmodifiableSet(new HashSet<String>());
	
	@SuppressWarnings("serial")
	protected Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
				
				put("classStyle", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return value == null? null : "class";
					}
				});
				
			}});
	
	@SuppressWarnings("serial")
	protected Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("classStyle");
		}});
	
	@SuppressWarnings("serial")
	protected Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
			}});
	
	private TagTemplate tagTemplate;
	
	public AbstractComponent() throws Throwable {
		this.loadConfiguration();
		this.loadTemplate();
	}
	
	protected void loadConfiguration(){
	}
	
	protected void loadTemplate() throws Throwable {
		
		PluginData pd = EntityContextPlugin.getEntity(PluginData.class);
		File file = new File(pd.getPath() + "/tags" + TEMPLATE);
		
		file = file.getCanonicalFile();
		
		TemplateLoader loader = new TemplateLoader();
		this.tagTemplate = loader.load(file, "UTF-8");
	}
	
	@Override
	public void applyTagTemplate(Map<String, Object> vars, Writer out) throws TemaException {
		try {
			tagTemplate.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemaException(e);
		}
	}

	@Override
	public void applyTagTemplate(Writer out, Object... vars) throws TemaException {
		try {
			tagTemplate.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new TemaException(e);
		}
	}
    
	@Override
	public Set<String> getAttributes() {
		return DEFAULT_ATTRS;
	}

	@Override
	public Set<String> getEmptyAttributes() {
		return DEFAULT_EMPTY_ATTRIBUTES;
	}

	@Override
	public Map<String, AttributeParser> getAttributesParser() {
		return DEFAULT_ATTRIBUTE_PARSERS;
	}

	@Override
	public Set<String> getProperties() {
		return DEFAULT_PROPS;
	}

	@Override
	public Map<String, AttributeParser> getPropertiesParse() {
		return DEFAULT_PROPERTY_PARSERS;
	}
	
}
