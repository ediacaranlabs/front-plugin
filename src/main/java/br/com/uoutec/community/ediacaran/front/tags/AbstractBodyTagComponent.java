package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.ComponentData;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public abstract class AbstractBodyTagComponent 
	extends BodyTagSupport implements ComponentData {

	private static final long serialVersionUID = -5353589232919296817L;

	private static final String TAG_CONTEXT = Component.class.getSimpleName() + ":CONTEXT";
	
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
	
	protected Component component;
	
	protected void beforeBuildComponent(Component component) {
	}

	protected void afterBuildComponent(Component component) {
	}
	
    public void doInitBody() throws JspException {
    	
		component.setProperty(getClass().getName() + ":CONTEXT", this);
		
    	registerParentTag();
    	
		beforeBuildComponent(component);
		
    }
	
    public int doAfterBody() throws JspException {
    	
    	try {
    		component.build();
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

    protected Writer getOut() {
    	return getBodyContent().getEnclosingWriter();
    }
    
    private void registerParentTag() {
		this.parentTag = getProperty(TAG_CONTEXT);
		setProperty(TAG_CONTEXT, this);
    }

    private void unregisterParentTag() {
		this.parentTag = null;
    }
    
    public void setBodyContent(BodyContent b) {
    	super.setBodyContent(b);
    	
    	if(component == null) {
        	component = createComponent();
        	component.setComponentData(this);
        	this.uniqueID = component.getId();
    	}
    	
    	//component.setOut(getBodyContent().getEnclosingWriter());
    	component.setOut(getOut());
    	
    	if(uniqueID == null) {
        	this.uniqueID = component.getId();
    	}
    	
    }
    
    public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
    		
    	if(component == null) {
        	component = createComponent();
        	component.setComponentData(this);
    	}
    		
    	component.setPageContext(pageContext);
    	
    	if(uniqueID == null) {
        	this.uniqueID = component.getId();
    	}
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
