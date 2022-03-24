package br.com.uoutec.community.ediacaran.front.tags;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.components.Component;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;
import br.com.uoutec.community.ediacaran.plugins.PluginType;

@Tag(
	name="load-data", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class LoadDataTag  extends SimpleTagSupport {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String file;
	
	private String var;
	
	public LoadDataTag() {
	}
	
    public void doTag() throws JspException, IOException {
    	Component tagComponent = new Component();
    	tagComponent.setPageContext((PageContext) getJspContext());
    	
    	File baseWebApp = new File(getBasePath());
    	Map<Object,Object> dta = ReadData.loadData(
    								file, 
    								baseWebApp, 
    								new File(baseWebApp, tagComponent.getRequestPath())
								);
    	
    	super.getJspContext().setAttribute(var == null? "vars" : var, dta);
	}

    private String getBasePath() {
    	PluginType pt = EntityContextPlugin.getEntity(PluginType.class);
    	//TODO: constants
    	return pt.getConfiguration().getMetadata().getPath().getBase() + "/public";
    }
    
    protected void applyTemplate(String template, 
    		Map<String,Object> vars, Writer out){
    }

	public String getFile() {
		return file;
	}

	@TagAttribute
	public void setFile(String file) {
		this.file = file;
	}

	public String getVar() {
		return var;
	}

	@TagAttribute
	public void setVar(String var) {
		this.var = var;
	}

}
