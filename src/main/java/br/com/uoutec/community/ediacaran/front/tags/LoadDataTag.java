package br.com.uoutec.community.ediacaran.front.tags;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import br.com.uoutec.community.ediacaran.front.tags.doc.BodyTypes;
import br.com.uoutec.community.ediacaran.front.tags.doc.Tag;
import br.com.uoutec.community.ediacaran.front.tags.doc.TagAttribute;
import br.com.uoutec.community.ediacaran.web.tomcat.TomcatServerBootstrap;

@Tag(
	name="load-data", 
	uri="https://www.uoutec.com.br/ediacaran/tags/bootstrap4/components", 
	bodycontent=BodyTypes.SCRIPTLESS
)
public class LoadDataTag  extends AbstractSimpleComponent {
	
	/* ------------ Attr ---------------*/
	
	/* ------------ Prop ---------------*/
	
	private String file;
	
	private String var;
	
	public LoadDataTag() {
	}
	
    protected void beforeApplyTemplate(String template, Map<String,Object> vars, 
    		Writer out) throws IOException {
    	
    	TomcatServerBootstrap sb     = null;//(ServerBootstrap) ApplicationBootstrapProvider.getBootstrap();
    	Map<Object,Object> dta = ReadData.loadData(
    								file, 
									sb.getWebapp()/*.getCanonicalFile()*/, 
									file.startsWith("/")? 
										sb.getWebapp() : 
										new File(sb.getWebapp(), super.getRequestPath())
								);
    	
    	super.getJspContext().setAttribute(var == null? "vars" : var, dta);
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

	@Override
	protected String getDefaultTemplate() {
		return null;
	}

}
