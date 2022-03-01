package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.front.theme.TagTemplate.VarParser;
import br.com.uoutec.community.ediacaran.front.theme.ThemeException;

public abstract class AbstractSimpleComponent 
	extends SimpleTagSupport
	implements TagComponenetInfo {

	private	String id;

	private String extAttrs;
	
	private String template;
	
	private String classStyle;
	
	private boolean wrapper;
	
	private String style;
	
	private String align;
	
	private VarParser content;

	private TagComponent tagComponent;
	
    public void doTag() throws JspException, IOException {
    	try {
    		tagComponent.applyTemplate();
    	}
	    catch(ThemeException e) {
	    	throw new JspException(e);
	    }
    }

    public void setJspContext(JspContext pc) {
    	
    	super.setJspContext(pc);
    	
    	tagComponent = createTagComponent();
    	tagComponent.setTag(this);
    	tagComponent.setPageContext((PageContext)pc);
    	tagComponent.setOut(pc.getOut());
    }

    public TagComponent getTagComponent() {
		return tagComponent;
	}

	public Object setProperty(String name, Object newValue) {
    	return tagComponent.setProperty(name, newValue);
    }

    public Object getProperty(String name) {
    	return tagComponent.getProperty(name);
    }
    
    public Object getProperty(String name, Object defaultValue) {
    	return tagComponent.getProperty(name, defaultValue);
    }
    
    public String getDefaultTemplate() {
    	throw new UnsupportedOperationException();
    }
    
    protected TagComponent createTagComponent() {
    	return new TagComponent();
    }
    
    protected Object getParentTag() {
    	return tagComponent.getParentTag();
    }
    
    protected VarParser toVarParser() {
    	return new JspFragmentVarParser(getJspBody());
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
		return id;
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
