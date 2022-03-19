package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.components.ComponentData;
import br.com.uoutec.community.ediacaran.front.components.EscapeVarParser;
import br.com.uoutec.community.ediacaran.front.components.JspFragmentVarParser;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.ComponentTemplate.VarParser;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public abstract class AbstractSimpleTagComponent 
	extends SimpleTagSupport
	implements ComponentData {

	private	String id;

	private String uniqueID;
	
	private String extAttrs;
	
	private String template;
	
	private String classStyle;
	
	private boolean wrapper;
	
	private String style;
	
	private String align;
	
	private VarParser content;

	private boolean escapeContent;
	
	private Component component;
	
    public void doTag() throws JspException, IOException {
    	try {
    		component.build();
    	}
	    catch(ThemeException e) {
	    	throw new JspException(e);
	    }
    }

    public void setJspContext(JspContext pc) {
    	
    	super.setJspContext(pc);
    	
    	component = createComponent();
    	component.setComponentData(this);
    	component.setPageContext((PageContext)pc);
    	component.setOut(pc.getOut());
    	this.uniqueID = component.getId();
    }

    public Component getComponent() {
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
    	return component.getParentTag();
    }
    
    protected VarParser toVarParser() {
    	return escapeContent? new EscapeVarParser(getJspBody()) : new JspFragmentVarParser(getJspBody());
    	//return new JspFragmentVarParser(getJspBody());
	}
	
	public VarParser getContent() {
		return content == null? toVarParser() : content;
	}

	public void setContent(VarParser content) {
		this.content = content;
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

	public boolean isEscapeContent() {
		return escapeContent;
	}

	public void setEscapeContent(boolean escapeContent) {
		this.escapeContent = escapeContent;
	}
	
}
