package br.com.uoutec.community.ediacaran.front.tags;

import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.theme.TagTemplate.VarParser;

public class FormTag extends AbstractSimpleComponent {

	public static final String TEMPLATE = "/components/form";
	
	public static final String FORM = FormTag.class.getSimpleName() + ":form";
	
	public static final String VERTICAL_FORM = FormTag.class.getSimpleName() + ":vertical_form";
	
	public static final String VERTICAL_FORM_VALUE = "true";
	
	/* ------------ Attr ---------------*/
	
	private String acceptCharset;
	
	private String action;
	
	private String enctype;
	
	private String method;
	
	private String target;
	
	private String update;
	
	/* ------------ Prop ---------------*/
	
	private String style; // inline, horizontal, vertical

	private VarParser content;
	
	public FormTag() {
	}

	public void beforePrepareVars(Map<String, Object> vars) {
		this.content = new JspFragmentVarParser(getJspBody());
	}
	
    protected void applyTemplate(String template, 
    		Map<String,Object> vars, Writer out){
    	Object oldForm = super.setProperty(FORM, this);
    	super.applyTemplate(template, vars, out);
    	super.setProperty(FORM, oldForm);
    }
	
    protected String getDefaultTemplate() {
    	return TEMPLATE;
    }
	
	public String getAcceptCharset() {
		return acceptCharset;
	}

	public void setAcceptCharset(String acceptCharset) {
		this.acceptCharset = acceptCharset;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEnctype() {
		return enctype;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public VarParser getContent() {
		return content;
	}

	public void setContent(VarParser content) {
		this.content = content;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}
	
}
