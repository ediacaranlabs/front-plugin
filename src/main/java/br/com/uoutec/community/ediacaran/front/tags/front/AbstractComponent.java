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
import br.com.uoutec.community.ediacaran.system.tema.TemplateLoader;
import br.com.uoutec.community.ediacaran.system.tema.ThemeException;

public abstract class AbstractComponent implements Component{

	protected String template;
	
	@SuppressWarnings("serial")
	protected Set<String> default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("id");
			add("classStyle");
		}});

	protected Set<String> DEFAULT_EMPTY_ATTRIBUTES = 
			Collections.unmodifiableSet(new HashSet<String>());
	
	
	@SuppressWarnings("serial")
	protected Map<String, AttributeParser> default_attribute_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
				
				put("classStyle", new AttributeParserImp() {
					
					@Override
					public String toName(String value, Object component) {
						return value == null? null : "class";
					}
				});
				
			}});
	
	
	@SuppressWarnings("serial")
	protected Set<String> default_props = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("classStyle");
		}});
	
	
	@SuppressWarnings("serial")
	protected Map<String, AttributeParser> default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
			}});
	
	private TagTemplate tagTemplate;
	
	public AbstractComponent() {
		this.loadConfiguration();
		this.loadTemplate();
	}
	
	protected void loadConfiguration(){
	}
	
	protected void loadTemplate() {
		
		try {
			PluginData pd = EntityContextPlugin.getEntity(PluginData.class);
			File file = new File(pd.getPath() + "/tags" + template);
			
			file = file.getCanonicalFile();
			
			TemplateLoader loader = new TemplateLoader();
			this.tagTemplate = loader.load(file, "UTF-8");
		}
		catch(Throwable e) {
			throw new ThemeException(e);
		}
	}
	
	@Override
	public void applyTagTemplate(Map<String, Object> vars, Writer out) throws ThemeException {
		try {
			tagTemplate.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new ThemeException(e);
		}
	}

	@Override
	public void applyTagTemplate(Writer out, Object... vars) throws ThemeException {
		try {
			tagTemplate.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new ThemeException(e);
		}
	}
    
	@Override
	public Set<String> getAttributes() {
		return default_attrs;
	}

	@Override
	public Set<String> getEmptyAttributes() {
		return DEFAULT_EMPTY_ATTRIBUTES;
	}

	@Override
	public Map<String, AttributeParser> getAttributesParser() {
		return default_attribute_parsers;
	}

	@Override
	public Set<String> getProperties() {
		return default_props;
	}

	@Override
	public Map<String, AttributeParser> getPropertiesParse() {
		return default_property_parsers;
	}
	
}
