package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.brandao.brutos.bean.BeanInstance;

public abstract class AbstractTag extends SimpleTagSupport{

	public static final String ID_COUNT   = "_component_id_count";

	public static final String ATTR_FORMAT = "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG = AbstractTag.class.getSimpleName() + ":parent";
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_ATTRS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
			add("id");
			add("classStyle");
		}});

	protected static final Set<String> DEFAULT_EMPTY_ATTRIBUTES = 
			Collections.unmodifiableSet(new HashSet<String>());
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_ATTRIBUTE_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
				
				put("classStyle", new AttributeParserImp() {
					
					@Override
					public String toName(String value) {
						return value == null? null : "class";
					}
				});
				
			}});
	
	@SuppressWarnings("serial")
	protected static final Set<String> DEFAULT_PROPS = 
		Collections.unmodifiableSet(new HashSet<String>() {{
		}});
	
	@SuppressWarnings("serial")
	protected static final Map<String, AttributeParser> DEFAULT_PROPERTY_PARSERS = 
			Collections.unmodifiableMap(new HashMap<String, AttributeParser>(){{
			}});

	private	String id;

	private String extAttrs;
	
	private String template;
	
	private String classStyle;
	
	private boolean wrapper;
	
    public void doTag() throws JspException, IOException {
		
		if(wrapper) {
			StringBuilder b = new StringBuilder("<div ")
				.append(this.toAttrs())
				.append(" >");
			getJspContext().getOut().write(b.toString());
		}
    	
		Object oldParent = getProperty(PARENT_TAG);

		setProperty(PARENT_TAG, this);
    	doInnerTag();
    	setProperty(PARENT_TAG, oldParent);
    	
    	if(wrapper) {
    		getJspContext().getOut().write("</div>");
    	}
    }
	
    public void doInnerTag() throws JspException, IOException{
    	
    	try {
			Map<String, Object> vars = getValues();
			
			TemplatesManager.getTemplatesManager()
				.apply(
						this.getTemplate() == null? 
								getDefaultTemplate() : 
								getTemplate(), 
						vars, getJspContext().getOut()
				);
    	}
    	catch(Throwable e) {
    		throw new IllegalStateException(e);
    	}
    	
    }
    
    protected String getDefaultTemplate() {
    	throw new UnsupportedOperationException();
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

	public String toAttrs() {
		return getAttrList();
	}
	
	private String getAttrList() {
		try {
			StringBuilder sb = new StringBuilder();
			BeanInstance i = new BeanInstance(this);
			Map<String, AttributeParser> parsers = getAttributeParsers();
			Set<String> emptyAttrs = getEmptyAttributes();
			
			for(String p: getDefaultAttributes()) {
				
				Object v = i.get(p);
				
				if(v == null || emptyAttrs.contains(p)) {
					continue;
				}
				
				if(sb.length() != 0) {
					sb.append(" ");
				}
				
				AttributeParser parser = parsers.get(p);
				
				p = parser == null? p : parser.toName(p);
				v = parser == null? v : parser.toValue(v);
				
				if(p != null) {
					sb.append(p).append("=\"").append(v).append("\"");
				}
				else {
					sb.append(v);
				}
				
			}
			
			if(this.extAttrs != null) {
				
				if(sb.length() != 0) {
					sb.append(" ");
				}
				
				sb.append(this.extAttrs);
			}
			
			return sb.toString();
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}
	
	public Map<String, Object> getValues() {
		try {
			BeanInstance i = new BeanInstance(this);
			Map<String, AttributeParser> parsers = getPropertyParsers();
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("attr", this.getAttrList());
			
			for(String k: getDefaultProperties()) {
				
				Object v = i.get(k);
				
				AttributeParser parser = parsers.get(k);
				
				k = parser == null? k : parser.toName(k);
				v = parser == null? v : parser.toValue(v);
				
				if(k != null && !k.isEmpty()) {
					map.put(k, v);
				}
			}
			
			return map;
		}
		catch(Throwable e) {
			throw new IllegalStateException(e);
		}
	}
	
    protected Object setProperty(String name, Object newValue) {
		Object old = this.getJspContext().getAttribute(name);
		this.getJspContext().setAttribute(name, newValue);
    	return old;
    }

    protected Object getProperty(String name) {
    	return getProperty(name, null);
    }
    
    protected Object getProperty(String name, Object defaultValue) {
		Object val = (Integer) this.getJspContext().getAttribute(name);
    	return val == null? defaultValue : val;
    }
    
	public String getExtAttrs() {
		return extAttrs;
	}

	public void setExtAttrs(String extAttrs) {
		this.extAttrs = extAttrs;
	}

	public String getId() {
		
		if(id == null) {
			Integer acc = (Integer) this.getJspContext().getAttribute(ID_COUNT);
			getJspContext().setAttribute(ID_COUNT, acc = acc == null? 0 : acc.intValue() + 1);
			return id = String.valueOf(acc);
		}
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean isWrapper() {
		return wrapper;
	}

	public void setWrapper(boolean wrapper) {
		this.wrapper = wrapper;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}
	
}
