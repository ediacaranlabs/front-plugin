package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.brandao.brutos.bean.BeanInstance;

import br.com.uoutec.application.bean.Bean;
import br.com.uoutec.application.security.ContextSystemSecurityCheck;
import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.ComponentData;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public abstract class AbstractBodyTagComponent 
	extends BodyTagSupport implements ComponentData {

	private static final long serialVersionUID = -5353589232919296817L;

	//private static final String TAG_CONTEXT = Component.class.getSimpleName() + ":CONTEXT";
	
	private static final String PARENT_PROPERTY = Component.class.getSimpleName() + ":PARENT";
	
	public static final String WRAPPER_TEMPLATE		= "/components/wrapper";
	
	public static final String ID_COUNT				= "_component_id_count";

	public static final String ATTR_FORMAT			= "([a-z-_]+)=([^\\;]+)";

	public static final String PARENT_TAG			= "Component:parent";
	
	private String uniqueID;
	
	private	String id;

	private String extAttrs;
	
	private String template;
	
	private String classStyle;
	
	private boolean wrapper;
	
	//private Object oldParent;
	
	private String style;
	
	private String align;
	
	private Object parentTag;
	
	private LocalizationContext bundle;
	
	protected Component component;
	
	private Bean bean;
	
	public AbstractBodyTagComponent() {
		this.bean = ContextSystemSecurityCheck.doPrivileged(()->new Bean(this, this.getClass()));
	}
	
	@Override
    public Object getAttribute(String name) {
    	return ContextSystemSecurityCheck.doPrivileged(()->bean.get(name));
    }
	
	protected void beforeBuildComponent(Component component) {
	}

	protected void buildComponent(Component component) throws ThemeException, IOException {
		component.build();
	}
	
	protected void afterBuildComponent(Component component) {
	}
	
    public int doStartTag() throws JspException {

    	component = createComponent();
    	this.uniqueID = component.getUniqueID();

    	return super.doStartTag();
    }
	
    public int doEndTag() throws JspException {
    	this.component = null;
    	return super.doEndTag();
    }
    
    public void doInitBody() throws JspException {

    	component.setComponentData(this);
    	component.setPageContext(pageContext);
    	component.setOut(getOut());
    	
		component.setProperty(getClass().getName() + ":CONTEXT", this);
		
    	registerParentTag();
    	
		beforeBuildComponent(component);
		
    }
	
    public int doAfterBody() throws JspException {
    	
    	try {
    		buildComponent(component);
    	}
	    catch(ThemeException e) {
	    	throw new JspException(e);
	    } 
    	catch (IOException e) {
	    	throw new JspException(e);
		}
    	
		component.setProperty(getClass().getName() + ":CONTEXT", null);
		
		unregisterParentTag();
		
		afterBuildComponent(component);
		
    	return SKIP_BODY;
    }
    
    public Component getComponent() {
    	return component;
    }
    
    public Map<String, Object> getProperties(Set<String> defaultProperties, Set<String> emptyProperties){
		Map<String, Object> map = new HashMap<String, Object>();
		
		BeanInstance i = 
			AccessController.doPrivileged(new PrivilegedAction<BeanInstance>() {
            public BeanInstance run() {
                return new BeanInstance(AbstractBodyTagComponent.this);
            }
        });

		for(String p: defaultProperties) {
			final String k = p;
			
			Object v = ContextSystemSecurityCheck.doPrivileged(()->{
				return i.get(k);
			});
			
			
			if(emptyProperties != null && emptyProperties.contains(p)) {
				continue;
			}
			
			if(bundle != null && v instanceof String) {
				String key = (String)v;
				if(key.startsWith("#{") && key.endsWith("}")) {
					key = key.substring(2, key.length() - 1);
					v = bundle.getResourceBundle().getObject((String)key);
				}
			}
			
			map.put(p, v);
		}
		
		return map;
    }
    
    protected Writer getOut() {
    	return getBodyContent().getEnclosingWriter();
    }
    
    /*
    private void registerParentTag() {
		this.parentTag = getProperty(TAG_CONTEXT);
		setProperty(TAG_CONTEXT, this);
    }

    private void unregisterParentTag() {
		this.parentTag = null;
    }
    */
    
	@SuppressWarnings("unchecked")
    protected void registerParentTag() {
		LinkedList<Object> parent = (LinkedList<Object>) getProperty(PARENT_PROPERTY);
    	
    	if(parent == null) {
    		parent = new LinkedList<>();
    		setProperty(PARENT_PROPERTY, parent);
    		parentTag = null;
    	}
    	else {
    		parentTag = parent.getLast();
    	}
    	
    	parent.add(this);
		//parentTag = getProperty(TAG_CONTEXT);
		//setProperty(TAG_CONTEXT, this);
    }

	@SuppressWarnings("unchecked")
    protected void unregisterParentTag() {
    	
    	LinkedList<Object> parent = (LinkedList<Object>) getProperty(PARENT_PROPERTY);
    	
    	if(parent != null) {
    		
    		parent.removeLast();
    		
    		if(parent.isEmpty()) {
    			setProperty(PARENT_PROPERTY, null);	
    		}
    		
    	}
    	
		parentTag = null;
    	
		//setProperty(TAG_CONTEXT, parentTag);
		//parentTag = null;
    }
    
    public void setBodyContent(BodyContent b) {
    	super.setBodyContent(b);
    	/*
    	if(component == null) {
        	component = createComponent();
        	component.setComponentData(this);
        	this.uniqueID = component.getUniqueID();
    	}
    	
    	component.setOut(getOut());
    	
    	if(uniqueID == null) {
        	this.uniqueID = component.getUniqueID();
    	}
    	*/
    }
    
    public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
    	/*
    	if(component == null) {
        	component = createComponent();
        	component.setComponentData(this);
    	}
    		
    	component.setPageContext(pageContext);
    	
    	if(uniqueID == null) {
        	this.uniqueID = component.getUniqueID();
    	}
    	*/
    }
    
    public Component getTagComponent() {
		return component;
	}

	public Object setProperty(String name, Object newValue) {
    	return component.setProperty(name, newValue);
    }

    public Object getProperty(String name) {
    	return component.getProperty(name);
    }
    
    public Object getProperty(String name, Object defaultValue) {
    	return component.getProperty(name, defaultValue);
    }
    
    public String getDefaultTemplate() {
    	throw new UnsupportedOperationException();
    }
    
    public String getWrapperTemplate() {
    	throw new UnsupportedOperationException();
    }
    
    protected Component createComponent() {
    	return new Component();
    }
    
    protected Object getParentTag() {
    	return parentTag;
    }
    
	public boolean isWrapper() {
		return wrapper;
	}

	public void setWrapper(boolean wrapper) {
		this.wrapper = wrapper;
	}
    
	public String getExtAttrs() {
		return extAttrs;
	}

	@TagAttribute
	public void setExtAttrs(String extAttrs) {
		this.extAttrs = extAttrs;
	}

	public String getId() {
		return id == null? uniqueID : id;
	}

	@TagAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getTemplate() {
		return template;
	}

	@TagAttribute
	public void setTemplate(String template) {
		this.template = template;
	}

	public LocalizationContext getBundle() {
		return bundle;
	}

	@TagAttribute
	public void setBundle(LocalizationContext bundle) {
		this.bundle = bundle;
	}

	public String getClassStyle() {
		return classStyle;
	}

	@TagAttribute
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public String getStyle() {
		return style;
	}

	@TagAttribute
	public void setStyle(String style) {
		this.style = style;
	}

	public String getAlign() {
		return align;
	}

	@TagAttribute
	public void setAlign(String align) {
		this.align = align;
	}
	    
    
}
