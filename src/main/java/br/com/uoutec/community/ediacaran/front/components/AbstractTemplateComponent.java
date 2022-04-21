package br.com.uoutec.community.ediacaran.front.components;

import java.io.File;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.uoutec.community.ediacaran.front.theme.PropertyParser;
import br.com.uoutec.community.ediacaran.front.theme.PropertyParserImp;
import br.com.uoutec.community.ediacaran.front.theme.TemplateComponent;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate;
import br.com.uoutec.community.ediacaran.front.theme.TemplateLoader;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginType;

public abstract class AbstractTemplateComponent 
	implements TemplateComponent{

	protected String template;
	
	@SuppressWarnings("serial")
	protected Set<String> default_attrs = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("id");
			add("classStyle");
			add("style");
			add("align");
		}});

	@SuppressWarnings("serial")
	protected Set<String> default_empty_attributes = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("classStyle");
			add("style");
		}});
	
	
	@SuppressWarnings("serial")
	protected Map<String, PropertyParser> default_attribute_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(){{
				
				put("classStyle", new PropertyParserImp() {
					
					@Override
					public String toName(String value, PropertiesComponentTemplate component) {
						return value == null? null : "class";
					}
					
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null || component.getProperty("style") != null? null : value;
					}
					
				});

				put("style", new PropertyParserImp() {

					@Override
					public String toName(String value, PropertiesComponentTemplate component) {
						return value == null? null : "class";
					}
					
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						
						Object style = component.getProperty("style");
						Object align = component.getProperty("align");
						Object classStyle = component.getProperty("classStyle");
						
						StringBuilder sb = new StringBuilder();
						
						if(classStyle != null) {
							sb.append(classStyle);
						}
						
						if(align != null) {
							sb.append("center".equals(align)? " mx-auto d-block" : " float-" + align);
						}
						
						if(style != null) {
							sb.append(" ").append(component.getType()).append("-").append(style);
						}
						
						return sb.toString();
					}
					
					
				});

				put("align", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return null;
					}
					
				});
				
			}});
	
	
	@SuppressWarnings("serial")
	protected Set<String> default_props = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("id");
			add("classStyle");
			add("style");
			add("align");
		}});
	
	
	@SuppressWarnings("serial")
	protected Map<String, PropertyParser> default_property_parsers = 
			Collections.unmodifiableMap(new HashMap<String, PropertyParser>(){{
				
				put("align", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						if("center".equals(value)) {
							return " mx-auto d-block";
						}
						else
							return value == null? null : " float-" + value;
					}
					
				});
				
				put("style", new PropertyParserImp() {
					
					@Override
					public Object toValue(Object value, PropertiesComponentTemplate component) {
						return value == null? "" : component.getType()+ value;
					}
				});
				
			}});
	
	private ComponentTemplate componentTemplate;
	
	public AbstractTemplateComponent() {
		this.loadConfiguration();
		//this.loadTemplate();
	}
	
	public void loadConfiguration(){
	}
	
	public void loadTemplate() {
		
		String t = "/themes" + template;
		
		try {
			PluginType pd = EntityContextPlugin.getEntity(PluginType.class);
			File file = new File(pd.getConfiguration().getMetadata().getPath().getBase() + t);
			
			file = file.getCanonicalFile();
			
			TemplateLoader loader = new TemplateLoader();
			this.componentTemplate = loader.load(file, "UTF-8");
		}
		catch(Throwable e) {
			throw new ThemeException("load template fail: " + t, e);
		}
		
	}
	
	@Override
	public String getTemplate() {
		return template;
	}
	
	@Override
	public void build(Map<String, Object> vars, Writer out) throws ThemeException {
		try {
			componentTemplate.toWriter(out, vars);
		}
		catch(Throwable e) {
			throw new ThemeException(e);
		}
	}

	@Override
	public void build(Writer out, Object... vars) throws ThemeException {
		try {
			componentTemplate.toWriter(out, vars);
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
		return default_empty_attributes;
	}

	@Override
	public Map<String, PropertyParser> getAttributesParser() {
		return default_attribute_parsers;
	}

	@Override
	public Set<String> getProperties() {
		return default_props;
	}

	@Override
	public Map<String, PropertyParser> getPropertiesParser() {
		return default_property_parsers;
	}
	
}
