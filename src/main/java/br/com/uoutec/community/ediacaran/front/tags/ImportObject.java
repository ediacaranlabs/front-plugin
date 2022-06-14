package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager;
import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.plugins.EntityContextPlugin;

@Tag(
	name="import-object", 
	uri="https://www.uoutec.com.br/ediacaran/tags/components", 
	bodycontent=BodyTypes.EMPTY
)
public class ImportObject extends SimpleTagSupport {
	
	private String id;
	
	private String var;
	
	public ImportObject() {
	}

    public void doTag() throws JspException, IOException {
    	ObjectsManager om = EntityContextPlugin.getEntity(ObjectsManager.class);
    	Object o = om.getObject(id);
    	((PageContext)getJspContext()).getRequest().setAttribute(var, o);
    }

	public String getId() {
		return id;
	}

	@TagAttribute(required=true)
	public void setId(String id) {
		this.id = id;
	}

	public String getVar() {
		return var;
	}

	@TagAttribute(required=true)
	public void setVar(String var) {
		this.var = var;
	}
	
}
